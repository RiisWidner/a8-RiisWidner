package Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Game.GolObserver;

public class GolModel {
	
	private boolean[][] currentState; // = new boolean[10][10];
	private List<GolObserver> observers = new ArrayList<GolObserver>();
	private GolView view;
	
	public GolModel(int x, int y, GolView view) {
		this.view = view;
		
		currentState = new boolean[x][y];
		for (int r = 0; r < currentState.length; r++) {
            for (int c = 0; c < currentState[0].length; c++) {
                currentState[r][c] = (Math.random() < 0.45);  // 25% probability that the cell is alive.
            }
		}
		
	}
	
	public boolean[][] getCurrentState() {
		return Arrays.copyOf(currentState, currentState.length);
	}
	
	public boolean getValue(int x, int y) {
		return currentState[x][y];
	}
	
	public void setState(boolean[][] temp) {
		
		for (int i = 0; i < currentState.length; i++) {
            for (int j = 0; j < currentState[0].length; j++) {
                currentState[i][j] = temp[i][j];
        	}
		}
		view.setDisplay(currentState);
	}

	public void addObserver(GolObserver o) {
		observers.add(o);
	}
	
	public void removeObserver(GolObserver o) {
		observers.remove(o);
	}
	
	public boolean checkLife(int x, int y) {
		return currentState[x][y];
	}
	
	public void setNewState(boolean[][] temp) {
		for (int i = 0; i < currentState.length; i++) {
            for (int j = 0; j < currentState[0].length; j++) {
                currentState[i][j] = temp[i][j];
        	}
		}
		view.setBoard();
		
	}
		
	
	
	
}
