package GameOfLife;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import Board.BoardController;
import Board.BoardModel;
import Board.BoardPanel;

public class GameOfLifeView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private enum ViewState {START_MENU, ENVIRONMENT_VIEW};
	
	public ViewState state;
	
	private BoardModel boardModel;
	private BoardPanel boardView;
	private BoardController boardController;
	
	public GameOfLifeView() {
		super();
		setTitle("Conway's Game of Life");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initWindow();
		showBoardView();
		run();
	}
	
	private void initWindow() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) Math.round(screenSize.width*0.5);
		
		int centrX = screenSize.width/2-(width/2);
		int centrY = screenSize.height/2 - (width/2);

		setSize(new Dimension(width, width));
		setLocation(centrX, centrY);
	}

	public void showBoardView() {
		boardModel = new BoardModel(40,40);
		boardView = new BoardPanel(boardModel);
		boardController = new BoardController(boardModel, boardView);
		state = ViewState.ENVIRONMENT_VIEW;
		showView();
	}
	
	private void showView() {
		getContentPane().removeAll();
		getContentPane().invalidate();

		if (state == ViewState.ENVIRONMENT_VIEW) add(boardView);
		getContentPane().revalidate();
	}
	
	private void run() {
		setResizable(false);
		setVisible(true);
	}


	public static void main(String[] args) {
		new GameOfLifeView();
	}
	
}