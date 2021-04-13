package Board;

public class Position {

	public final int x;
	public final int y;
	public final int hash;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
		hash = (13 + x) * 13 + y;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || this.getClass() != obj.getClass()) return false;
		Position pos = (Position) obj;
		return this.x == pos.x && this.y == pos.y;
	}
	
	@Override
	public int hashCode() {
        return hash;
    }
	
}
