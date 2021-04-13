package Board;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.BorderFactory;

public class BoardPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	static final int SPEED_MIN = 1;
	static final int SPEED_MAX = 10;
	static final int SPEED_INIT = 5; 

	private final Color background = Color.LIGHT_GRAY;
	
	private BoardModel model;
	
	private JPanel boardPanel;
	private JPanel settingsPanel;
	
	private JLabel timeLabel;
	
	private JButton playpause;
	private JButton restart;
	private JButton next;
	private JButton clear;
	private JSlider speed;
	
	public BoardPanel(BoardModel model) {
		super();
		this.model = model;
		
		initView();
	}
	
	public void initView() {
		
		initEnvironmentPanel();
		initInfoPanel();
		
		setLayout(new BorderLayout());
		add(boardPanel, BorderLayout.CENTER);
		add(settingsPanel, BorderLayout.SOUTH);
	}
	
	
	private void initEnvironmentPanel() {
		boardPanel = new JPanel();
		boardPanel.setName("Board");
	
		boardPanel.setLayout(new GridLayout(model.getHeight(), model.getWidth()));
		boardPanel.setBackground(background);
		
		for (int y = 0; y < model.getHeight(); y++) {
			for (int x = 0; x < model.getWidth(); x++) {
				boardPanel.add(model.getBoard().get(y).get(x));
			}
			
		}
		
	}
	
	private void initInfoPanel() {
		settingsPanel = new JPanel();
		
		settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.X_AXIS));
		settingsPanel.setBackground(background);
		JPanel timePanel = new JPanel();
		timePanel.setBorder(BorderFactory.createTitledBorder("Time"));
		timePanel.setBackground(background);

		timeLabel = new JLabel();
		timeLabel.setText("0");
		
		timePanel.add(timeLabel);
		
		JPanel controlPanel = new JPanel();
		controlPanel.setBorder(BorderFactory.createTitledBorder("Controls"));
		controlPanel.setBackground(background);
		playpause = new JButton("Play");
		restart = new JButton("Restart");
		next = new JButton("Next");
		clear = new JButton("Clear");
		controlPanel.add(playpause);
		controlPanel.add(restart);
		controlPanel.add(next);
		controlPanel.add(clear);

		JPanel speedPanel = new JPanel();
		speedPanel.setBorder(BorderFactory.createTitledBorder("Simulation Speed"));
		speedPanel.setBackground(background);

		speed = new JSlider(JSlider.HORIZONTAL,
                SPEED_MIN, SPEED_MAX, SPEED_INIT);
		speed.setBackground(background);
		speed.setMajorTickSpacing(1);
		speed.setMinorTickSpacing(1);
		speed.setPaintTicks(true);
		speed.setPaintLabels(true);
		
		speedPanel.add(speed);
		
		
		settingsPanel.add(timePanel);
		settingsPanel.add(controlPanel);
		settingsPanel.add(speedPanel);
	}
	
	protected JButton getPlayPauseButton() {
		return playpause;
	}
	
	protected JButton getRestartButton() {
		return restart;
	}
	
	protected JButton getNextButton() {
		return next;
	}
	
	protected JButton getClearButton() {
		return clear;
	}
	
	protected JLabel getTimeLabel() {
		return timeLabel;
	}
	
	protected JSlider getSpeedSlider() {
		return speed;
	}
	
}
