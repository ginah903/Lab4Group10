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

	/**
	 * Returns the ArrayList of cells in this row.
	 */
	public ArrayList<Cell> getCells() {
		return cells;
	}

	/**
	 * Sets an ArrayList of cells.
	 * @param cells
	 */
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
