package Board;

import java.util.ArrayList;
import java.util.List;

public class BoardModel {

	
	private List<List<BoardCell>> board;
	private List<List<Boolean>> nextState;
	
	private int width;
	private int height;
	private int speed = 5;
	private int time = 0;
	
	public BoardModel(int width, int height) {
		this.width = width;
		this.height = height;
		initBoard();
	}
	
	private void initBoard() {
		board = new ArrayList<List<BoardCell>>();
		nextState = new ArrayList<List<Boolean>>();
		for (int y = 0; y < height; y++) {
			ArrayList<BoardCell> boardRow = new ArrayList<BoardCell>();
			ArrayList<Boolean> stateRow = new ArrayList<Boolean>();
			for (int x = 0; x < width; x++) {
				boardRow.add(new BoardCell(new Position(x, y)));
				stateRow.add(false);
			}
			board.add(boardRow);
			nextState.add(stateRow);
		}
	}
	
	public int getHeight() {
		// TODO Auto-generated method stub
		return height;
	}

	public int getWidth() {
		// TODO Auto-generated method stub
		return width;
	}
	
	public List<List<BoardCell>> getBoard(){
		return board;
	}

	public void reset() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				board.get(y).get(x).setAlive(false);
			}
		}
		time = 0;
	}
	
	private Boolean getNextState(BoardCell cell) {
		
		int neighbors = 0;
		Position position = cell.getPosition();
		
		if (position.x - 1 >= 0) {
			int xPos = position.x-1;
			if (board.get(position.y).get(xPos).isAlive())
				neighbors++;
			
			if (position.y - 1 >= 0) 
				if (board.get(position.y-1).get(xPos).isAlive())
					neighbors++;
				
			if (position.y + 1 < height)
				if (board.get(position.y+1).get(xPos).isAlive())
					neighbors++;		
		}
		
		if (position.x+1 < width) {
			int xPos = position.x+1;
			if (board.get(position.y).get(xPos).isAlive())
				neighbors++;
			if (position.y - 1 >= 0) {
				if (board.get(position.y-1).get(xPos).isAlive())
					neighbors++;
			}
			if (position.y + 1 < height)
				if (board.get(position.y+1).get(xPos).isAlive())
					neighbors++;
		}
		
		if (position.y - 1 >= 0) 
			if (board.get(position.y-1).get(position.x).isAlive())
				neighbors++;
		
		if (position.y + 1 < height) 
			if (board.get(position.y+1).get(position.x).isAlive())
				neighbors++;
		
		
		if (cell.isAlive())
			return !(neighbors < 2 || neighbors > 3);
		else 
			return neighbors == 3;
	}

	public void updateBoard() {
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				nextState.get(y).set(x, getNextState(board.get(y).get(x)));
			}
		}
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				board.get(y).get(x).setAlive(nextState.get(y).get(x));
			}
		}
		time++;
		
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getTime() {
		// TODO Auto-generated method stub
		return time;
	}

}