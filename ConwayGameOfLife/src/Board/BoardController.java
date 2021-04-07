package Board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BoardController implements ActionListener, MouseListener, ChangeListener {

	private BoardModel model;
	private BoardPanel view;
	private Runnable simulate;
	private AtomicBoolean runSimulation = new AtomicBoolean(false);
	

	public BoardController(BoardModel model, BoardPanel view) {
		this.model = model;
		this.view = view;
		this.simulate = new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(100*(11-model.getSpeed()));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				model.updateBoard();
				view.getTimeLabel().setText(model.getTime()+"");
				view.repaint();
				
				if (runSimulation.get()) {
					SwingUtilities.invokeLater(simulate);
				}
				
			}
		};
		
		mapActions();
	}
	
	private void mapActions() {
		view.getPlayPauseButton().addActionListener(this);
		view.getRestartButton().addActionListener(this);
		for (int x = 0; x < model.getWidth(); x++) {
			for (int y = 0; y < model.getHeight(); y++) {
				model.getBoard().get(y).get(x).addMouseListener(this);
			}
			
		}
		view.getNextButton().addActionListener(this);
		view.getSpeedSlider().addChangeListener(this);
	}
	
	private void restartSimulation() {
		model.reset();

		view.getTimeLabel().setText(model.getTime()+"");
		view.repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == view.getPlayPauseButton()) {
			if (!runSimulation.get()) {
				view.getPlayPauseButton().setText("Pause");
				view.getNextButton().setEnabled(false);
				runSimulation.set(true);
				SwingUtilities.invokeLater(simulate);
			}
				
			else {
				runSimulation.set(false);
				view.getPlayPauseButton().setText("Play");
				view.getNextButton().setEnabled(true);
			}
			
		}
		
		else if (e.getSource() == view.getRestartButton()) {
			runSimulation.set(false);
			view.getPlayPauseButton().setText("Play");
			view.getNextButton().setEnabled(true);
			restartSimulation();
		}
		
		else if (e.getSource() == view.getNextButton()) {
			model.updateBoard();
			view.getTimeLabel().setText(model.getTime()+"");
			view.repaint();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() instanceof BoardCell) {
			BoardCell cell = (BoardCell) e.getSource();
			cell.setAlive(!cell.isAlive());
			view.repaint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == view.getSpeedSlider()) {
			model.setSpeed(view.getSpeedSlider().getValue());
		}
	}
}