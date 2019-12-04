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

public class GolView extends JPanel implements ActionListener, ChangeListener, SpotListener{
	private JSpotBoard grid;
	private JLabel message;
	private int gridSize;
	private List<GolViewListener> listeners = new ArrayList<GolViewListener>();
	private JSlider slider;
	
	public GolView() {
		setLayout(new BorderLayout());
		
		slider = new JSlider();
		slider.setValue(15);
		slider.setOrientation(SwingConstants.VERTICAL);
		slider.setMinimum(10);
		slider.setMaximum(500);
		slider.addChangeListener(this);
		
		gridSize = slider.getValue();
		
		add(slider, BorderLayout.EAST);
		
		grid = new JSpotBoard(gridSize, gridSize);
		message = new JLabel();
	
		
		
		add(grid, BorderLayout.CENTER);
		
		JPanel reset_message_panel = new JPanel();
		reset_message_panel.setLayout(new BorderLayout());
		
		JButton startButton = new JButton("Next Generation");
		startButton.setName("start");
		startButton.addActionListener(this);
		reset_message_panel.add(startButton, BorderLayout.EAST);
		reset_message_panel.add(message, BorderLayout.CENTER);
		
		add(reset_message_panel, BorderLayout.SOUTH);
		
		
		JPanel beginMessage = new JPanel();
		beginMessage.setLayout(new BorderLayout());
		
		JButton createLife = new JButton("Randomize");
		createLife.setName("createLife");
		createLife.addActionListener(this);
		reset_message_panel.add(createLife, BorderLayout.WEST);
		reset_message_panel.add(message, BorderLayout.CENTER);
		
		add(reset_message_panel, BorderLayout.SOUTH);
		
		
		JPanel clearMessage = new JPanel();
		clearMessage.setLayout(new BorderLayout());
		
		JButton clearButton = new JButton("Clear Board");
		clearButton.setName("clear");
		clearButton.addActionListener(this);
		clearMessage.add(clearButton, BorderLayout.SOUTH);
		clearMessage.add(message, BorderLayout.CENTER);
		
		add(clearMessage, BorderLayout.NORTH);
		
		grid.addSpotListener(this);
	}
	
	public void setBoard() {
		remove(grid);
		
		grid = new JSpotBoard(gridSize, gridSize);
		add(grid);
		grid.addSpotListener(this);
		
		validate();
		repaint();
	}
	
	public void setDisplay(boolean[][] state) {
		remove(grid);
		
		grid = new JSpotBoard(gridSize, gridSize);
		add(grid);
		grid.addSpotListener(this);
		
		validate();
		repaint();
		
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[0].length; j++) {
				if (state[i][j]) {
					grid.getSpotAt(i, j).setSpotColor(Color.BLACK);
					grid.getSpotAt(i, j).toggleSpot();
				} else if (!grid.getSpotAt(i, j).isEmpty()){
					grid.getSpotAt(i, j).clearSpot();
				}
			}
		}
		
	}
	
	public int getGridSize() {
		return gridSize;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton control = (JButton) e.getSource();
		String controlName = control.getName();
		if (controlName.equals("start")) {
			for(GolViewListener i : listeners) {
		i.handleGolViewEvent();
			}
		} else if (controlName.equals("createLife")){
			for(GolViewListener i : listeners) {
				i.appearModel();
			}
		} else {
			for(GolViewListener i : listeners) {
				i.clearBoard();
			}
		}
		
	}
	
	public void addGolViewListener(GolViewListener l) {
		listeners.add(l);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		gridSize = slider.getValue();
		for(GolViewListener i : listeners) {
			i.resize(gridSize);
		
		}
	}

	@Override
	public void spotClicked(Spot spot) {
		spot.setSpotColor(Color.BLACK);
		spot.toggleSpot();
		
		boolean[][] temp = new boolean[gridSize][gridSize];
		for(int i = 0; i < gridSize; i++) {
			for(int j = 0; j < gridSize; j++) {
				if (!grid.getSpotAt(i, j).isEmpty()) {
						temp[i][j] = true;
				} else {
					temp[i][j] = false;
				}
			}
		}
		
		for(GolViewListener i : listeners) {
			i.changeState(temp);
		
		}
		
	}

	@Override
	public void spotEntered(Spot spot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void spotExited(Spot spot) {
		// TODO Auto-generated method stub
		
	}

}
