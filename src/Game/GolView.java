package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Game.JSpotBoard;
import Game.GolViewListener;

public class GolView extends JPanel implements ActionListener, ChangeListener{
	private JSpotBoard grid;
	private JLabel message;
	private int gridSize;
	private List<GolViewListener> listeners = new ArrayList<GolViewListener>();
	private JSlider slider;
	
	public GolView() {
		setLayout(new BorderLayout());
		
		slider = new JSlider();
		slider.setValue(10);
		slider.setOrientation(SwingConstants.VERTICAL);
		slider.setMaximum(500);
		slider.addChangeListener(this);
		
		gridSize = slider.getValue();
		
		add(slider, BorderLayout.EAST);
		
		grid = new JSpotBoard(gridSize, gridSize);
		message = new JLabel();
	
		
		
		add(grid, BorderLayout.CENTER);
		
		JPanel reset_message_panel = new JPanel();
		reset_message_panel.setLayout(new BorderLayout());

		/* Reset button. Add ourselves as the action listener. */
		
		JButton startButton = new JButton("Start");
		startButton.addActionListener(this);
		reset_message_panel.add(startButton, BorderLayout.EAST);
		reset_message_panel.add(message, BorderLayout.CENTER);

		/* Add subpanel in south area of layout. */
		
		add(reset_message_panel, BorderLayout.SOUTH);


		

	}
	
	public void setDisplay(boolean[][] state) {
		
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[0].length; j++) {
				if (state[i][j]) {
					grid.getSpotAt(i, j).setSpotColor(Color.BLACK);
					grid.getSpotAt(i, j).toggleSpot();
				} else if (!grid.getSpotAt(i, j).isEmpty()){
					grid.getSpotAt(i, j).toggleSpot();
				}
			}
		}
		
	}
	
	public int getGridSize() {
		return gridSize;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		for(GolViewListener i : listeners) {
		i.handleGolViewEvent();
		}
	}
	
	public void addGolViewListener(GolViewListener l) {
		listeners.add(l);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		gridSize = slider.getValue();
		grid = new JSpotBoard(gridSize, gridSize);
		for(GolViewListener i : listeners) {
			i.resize(gridSize);
		
		}
	}

}
