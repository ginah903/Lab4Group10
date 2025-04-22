package hw4.maze;

public class Cell {
	private CellComponents left;
	private CellComponents right;
	private CellComponents up;
	private CellComponents down;
	
	/** Constructor for a cell with provided components on 
	 * the left, right, upper, and lower side
	 * 
	 * @param left The left component
	 * @param right The right component
	 * @param up The upper component
	 * @param down The lower component
	 */
	public Cell(CellComponents left, CellComponents right, CellComponents up, CellComponents down) {
		this.left = left;
		this.right = right;
		this.up = up;
		this.down = down;
	}

	// GETTERS AND SETTERS
	
	public CellComponents getLeft() {
		return left;
	}

	public void setLeft(CellComponents left) {
		if (left == null) {		
			this.left = CellComponents.WALL;
		} else {
			this.left = left;
		}
	}

	public CellComponents getRight() {
		return right;
	}

	public void setRight(CellComponents right) {
		if (right == null) {
			this.right = CellComponents.WALL;
		} else {
			this.right = right;
		}
	}

	public CellComponents getUp() {
		return up;
	}

	public void setUp(CellComponents up) {
		if (up == null) {
			this.up = CellComponents.WALL;
		} else {
			this.up = up;
		}
	}

	public CellComponents getDown() {
		return down;
	}

	public void setDown(CellComponents down) {
		if (down == null) {
			this.down = CellComponents.WALL;
		} else {
			this.down = down;
		}
	}
	
	/**
	 * Returns string representation of the Cell, prints out its
	 * four components.
	 */
	@Override
	public String toString() {
		return "Cell [left=" + left + ", right=" + right + ", up=" + up + ", down=" + down + "]";
	}


/*
 * Checks if two cells are equal, returns true if both cell's components are equal.
 * Necessary to satisfy assertEquals() method calls with Cell objects in the test files.
 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cell other = (Cell) obj;
		return down == other.down && left == other.left && right == other.right && up == other.up;
	}
	
	
	
	
	
}
