package hw4.maze;

import java.util.ArrayList;

public class Row {
	private ArrayList<Cell> cells;

	/**
	 * Creates a Row with a given list of cells.
	 * @param cells
	 */
	public Row(ArrayList<Cell> cells) {
		this.cells = cells;
	}

	// GETTERS AND SETTERS

	/**
	 * Returns the list of cells in this row.
	 * Changed return type from List<Cell> to ArrayList<Cell> 
	 * for compatibility with test files that explicitly use ArrayList.
	 */
	public ArrayList<Cell> getCells() {
		return cells;
	}

	public void setCells(ArrayList<Cell> cells) {
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
