package Board;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class BoardModel {

	private List<List<CellPanel>> board;
	private Map<Position,Boolean> nextState;
	private Map<Position,Boolean> initialState;
	private Map<Position,CellModel> relevantCells;
	
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
		board = new ArrayList<List<CellPanel>>();
		nextState = new Hashtable<Position,Boolean>();
		initialState = new Hashtable<Position,Boolean>();
		relevantCells = new Hashtable<Position,CellModel>();
		for (int y = 0; y < height; y++) {
			ArrayList<CellPanel> boardRow = new ArrayList<CellPanel>();
			ArrayList<Boolean> stateRow = new ArrayList<Boolean>();
			for (int x = 0; x < width; x++) {
				boardRow.add(new CellPanel(new Position(x, y)));
				stateRow.add(false);
			}
			board.add(boardRow);
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
	
	public List<List<CellPanel>> getBoard(){
		return board;
	}

	public void reset() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				board.get(y).get(x).getModel().setAlive(false);
				board.get(y).get(x).getModel().removeNeighbors();
			}
		}
		
		relevantCells.clear();
		
		for (Position position : initialState.keySet()) {
			board.get(position.y).get(position.x).getModel().setAlive(true);
			updateNeighbors(board.get(position.y).get(position.x).getModel());
		}
		
		time = 0;
	}
	
	private void getNextState(CellModel cell) {
	
		if (cell.isAlive() && (cell.numLivingNeighbors() < 2 || cell.numLivingNeighbors() > 3)) {
			nextState.put(cell.getPosition(), false);
		} else if (!cell.isAlive() && (cell.numNeighbors() == 3)) {
			nextState.put(cell.getPosition(), true);
		} else
			nextState.put(cell.getPosition(),cell.isAlive());
		
	}

	public void updateBoard() {
		
		for (CellModel relevantCell : relevantCells.values()) {
			getNextState(relevantCell);
		}
		
		for (Position position : nextState.keySet()) {
			if (relevantCells.containsKey(position) && nextState.get(position) != relevantCells.get(position).isAlive()) {
				CellModel relevantCell = relevantCells.get(position);
				relevantCell.setAlive(nextState.get(relevantCell.getPosition()));
				updateNeighbors(relevantCell);
			}
		}
		nextState.clear();
		
		time++;
		
	}
	
	public Map<Position, CellModel> getReleventCells(){
		return relevantCells;
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

	public void updateNeighbors(CellModel cell) {
		
		Position[] neighbors = new Position[8];
		int idx = 0;
		for (int deltaX = -1; deltaX < 2; deltaX++) {
			for (int deltaY = -1; deltaY < 2; deltaY++) {
				if (deltaX != 0 || deltaY != 0){
					neighbors[idx++] = new Position(cell.getPosition().x + deltaX, cell.getPosition().y + deltaY);
				}
			}
		}
		
		for (int i = 0; i < neighbors.length; i++) {
			updateReleventCell(neighbors[i], cell);
		}
		
	}
	
	private boolean onBoard(Position position) {

		return position.x < width && position.x >= 0 && position.y < height && position.y >= 0;
		
	}
	
	//Update relevant cells so that it does not get modified during iteration
	private void updateReleventCell(Position position, CellModel cell) {
		if (cell.isAlive()) {
			if (relevantCells.containsKey(position)) relevantCells.get(position).addNeighbor(cell);
			else {
				CellModel relevantCell = (onBoard(position)) ? board.get(position.y).get(position.x).getModel() : new CellModel(position);
				relevantCell.addNeighbor(cell);
				relevantCells.put(relevantCell.getPosition(), relevantCell);
			}
		} else if (relevantCells.containsKey(position)) {
			CellModel relevantCell = relevantCells.get(position);
			if (!relevantCell.isAlive()) {
				relevantCell.removeNeighbor(cell);
				if (!relevantCell.hasNeighbor() && !nextState.containsKey(relevantCell.getPosition())) relevantCells.remove(relevantCell.getPosition());
				if (!cell.hasNeighbor() && !nextState.containsKey(cell.getPosition())) relevantCells.remove(cell.getPosition());
			}
					
		}
		
	}

	public Map<Position,Boolean> getInitialState() {
		return initialState;
	}

	public void clear() {
		initialState.clear();
		reset();
	}

}
