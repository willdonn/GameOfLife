package Board;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class BoardPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	static final int SPEED_MIN = 1;
	static final int SPEED_MAX = 10;
	static final int SPEED_INIT = 5; 

	private BoardModel model;
	
	private JPanel boardPanel;
	private JPanel settingsPanel;
	
	private JLabel timeLabel;
	
	private JButton playpause;
	private JButton restart;
	private JButton next;
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
		boardPanel.setBackground(Color.gray);
		
		for (int y = 0; y < model.getHeight(); y++) {
			for (int x = 0; x < model.getWidth(); x++) {
				boardPanel.add(model.getBoard().get(y).get(x));
			}
			
		}
		
	}
	
	private void initInfoPanel() {
		settingsPanel = new JPanel();
		
		settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.X_AXIS));
		
		timeLabel = new JLabel();
		timeLabel.setText("0");
		
		playpause = new JButton("Play");
		restart = new JButton("Restart");
		
		next = new JButton("Next");
		
		speed = new JSlider(JSlider.HORIZONTAL,
                SPEED_MIN, SPEED_MAX, SPEED_INIT);
		
		speed.setMajorTickSpacing(5);
		speed.setMinorTickSpacing(1);
		speed.setPaintTicks(true);
		speed.setPaintLabels(true);
		
		settingsPanel.add(timeLabel);
		settingsPanel.add(playpause);
		settingsPanel.add(restart);
		settingsPanel.add(next);
		settingsPanel.add(speed);
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
	
	protected JLabel getTimeLabel() {
		return timeLabel;
	}
	
	protected JSlider getSpeedSlider() {
		return speed;
	}
	
}
