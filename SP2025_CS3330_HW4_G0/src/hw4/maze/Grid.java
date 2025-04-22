package hw4.maze;

public class Grid {
	private List<Row> rows;
	
	/**
	 * Creates a Grid with a given list of rows.
	 * @param rows
	 */
	public Grid(List<Row> rows) {
		this.rows = rows;
	}
	
	//GETTERS AND SETTERS
	public List<Row> getRows(){
		return rows;
	}
	
	public void setRows(List<Row> rows) {
		this.rows = rows;
	}
	
	/**
	 * Returns a string representation of the Grid, lists out all the rows.
	 */
	@Override
	public String toString() {
		return "Grid [rows=" + rows + "]";
	}
}
