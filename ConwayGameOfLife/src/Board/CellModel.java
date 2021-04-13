package Board;

import java.util.ArrayList;
import java.util.List;

public class CellModel {

	private List<CellModel> neighbors;
	private Position position;
	private boolean alive; 
	
	public CellModel(Position position) {
		this.position = position;
		this.neighbors = new ArrayList<CellModel>();
		this.alive = false;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public void setPosition(Position position) {
		this.position = position;
	}
	
	public Position getPosition() {
		return position;
	}
	
	public void addNeighbor(CellModel neighbor) {
		if (!neighbors.contains(neighbor)) {
			neighbors.add(neighbor);
			if (alive)
				neighbor.addNeighbor(this);
		}
	}
	
	public void removeNeighbor(CellModel neighbor) {
		if (neighbors.contains(neighbor)) {
			neighbors.remove(neighbor);
			neighbor.removeNeighbor(this);
		}
			
	}
	
	public boolean hasNeighbor() {
		return neighbors.size() > 0;
	}
	
	public int numNeighbors() {
		return neighbors.size();
	}

	public void removeNeighbors() {
		neighbors.clear();
	}

	public int numLivingNeighbors() {
		int living = 0;
		for (CellModel neighbor : neighbors) {
			if (neighbor.isAlive()) living++;
		}
		return living;
	}
	
}
