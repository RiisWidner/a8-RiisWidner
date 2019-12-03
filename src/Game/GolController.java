package Game;

import java.util.Arrays;

import com.sun.jdi.event.Event;

public class GolController implements GolViewListener, GolObserver{
	
	private GolView view;
	private GolModel model;
	
	public GolController(GolView view, GolModel model) {
		this.view = view;
		this.model = model;
		
		this.view.addGolViewListener(this);
		
		this.model.addObserver(this);
	}

	@Override
	public void handleGolViewEvent() {
		boolean [][] temp = new boolean[view.getGridSize()][view.getGridSize()];
		for(int i = 0; i < view.getGridSize(); i++)
			for (int  j = 0; j < view.getGridSize(); j++) {
				temp[i][j] = model.getCurrentState()[i][j];
			}
		    
		
		for(int i = 0; i < temp.length; i++) {
			for(int j = 0; j < temp[0].length; j++) {
				int neighbors = countLiveNeighbors(i, j);
				
				if (neighbors <= 1) {
					temp[i][j] = oneNeighbor();
				} else if (neighbors == 3) {
					temp[i][j] = threeNeighbors();
				} else if (neighbors > 3) {
					temp[i][j] = fourNeighbors();
				}
			}
		}
		
		update(model, temp);
		
	}


	@Override
	public void update(GolModel memory, boolean [][] change) {
		memory.setState(change);
		
	}
	
	private int countLiveNeighbors(int x, int y) {
		int temp = 0;
		if (x < view.getGridSize() - 1) {
			if (model.checkLife(x + 1, y)) {
			temp++;
			}
		}
		if (x > 0 ) {
			if (model.checkLife(x - 1, y)) {
			temp++;
			}
		}
		if (y > 0) {
			if (model.checkLife(x, y - 1)) {
			temp++;
		}
		}
		if (y < view.getGridSize() - 1) {
			if (model.checkLife(x, y + 1)) {
			temp++;
			}
		}
		if (y > 0 && x > 0) {
			if (model.checkLife(x - 1, y - 1)) {
			temp++;
			}
		}		
		if (y > 0 && x < view.getGridSize() - 1) {
			if (model.checkLife(x + 1, y - 1)) {
			temp++;
			}
		}
		if (y < view.getGridSize() - 1 && x > 0) {
			if (model.checkLife(x - 1, y + 1)) {
			temp++;
			}
		}	
		if (y < view.getGridSize() - 1 && x < view.getGridSize() - 1) {
			if (model.checkLife(x + 1, y + 1)) {
			temp++;
			}
		}
			
					
					
		
		
		
		return temp;
	}
 
	private boolean oneNeighbor() {
		return false;
	}
	
	private boolean threeNeighbors() {
		return true;
	}
	
	private boolean fourNeighbors() {
		return false;
	}
	
	public void resize(int size) {
		this.model.removeObserver(this);
		
		GolModel newModel = new GolModel(size, size, view);
		model = newModel;
		this.model.addObserver(this);
	}

}
