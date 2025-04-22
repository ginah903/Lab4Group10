package hw4.maze;

import java.util.List;

public class Row {
	private List<Cell> cells;
	
	/**
	 * Creates a Row with a given list of cells.
	 * @param cells
	 */
	public Row(List<Cell> cells) {
		this.cells = cells;
	}
	
	
	// GETTERS AND SETTERS
	public List<Cell> getCells() {
		return cells;
	}
	
	public void setCells(List<Cell> cells) {
		this.cells = cells;
		
		
	}

	/**
	 * Returns a string representation of the Row, lists out all the cells 
	 * in the same format as the test file. 
	 */
	@Override
	public String toString() {
		return "Row [cells=" + cells + "]";
	}
	
	
}
