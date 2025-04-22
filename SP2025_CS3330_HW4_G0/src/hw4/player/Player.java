package hw4.player;

import hw4.maze.Cell;
import hw4.maze.Row;

public class Player {
	private Row currentRow;
	private Cell currentCell;
	
	
	//constructor for player
	public Player(Row currentRow, Cell currentCell) {
		this.currentRow = currentRow;
		this.currentCell = currentCell;
	}
	
	//GETTERS AND SETTERS
	public Row getCurrentRow() {
        return currentRow;
    }

    public void setCurrentRow(Row currentRow) {
        this.currentRow = currentRow;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
    }
    
    /**
	 * Returns a string representation of the Player, lists out all the rows and cells.
	 */
    @Override
    public String toString() {
    	return "Player [currentCell=" + currentCell + ", currentRow=" + currentRow + "]";
    }
}
