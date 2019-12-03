package Game;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		GolView view = new GolView();
		GolModel model = new GolModel(view.getGridSize(), view.getGridSize(), view);
		GolController controller = new GolController(view, model);
		
	
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Game Of Life");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		main_frame.setContentPane(view);
	
		main_frame.pack();
		main_frame.setVisible(true);
	}
}
