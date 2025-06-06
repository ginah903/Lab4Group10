package hw4.player;

import hw4.maze.*;

public class Player {
	private Row currentRow;
	private Cell currentCell;
	
	
	/**
	 * Constructor for player given a row and cell.
	 * @param currentRow The current row
	 * @param currentCell The current cell
	 */
	public Player(Row currentRow, Cell currentCell) {
		this.currentRow = currentRow;
		this.currentCell = currentCell;
	}
	
	// GETTERS AND SETTERS
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
    
   public int getCurrentRowIndex(Grid grid) {
	   return grid.getRows().indexOf(currentRow);
   }
   
   public int getCurrentCellIndex() {
	   return currentRow.getCells().indexOf(currentCell);
   }
    
    /**
	 * Returns a string representation of the Player, lists out all the rows and cells.
	 */
    @Override
    public String toString() {
    	return "Player [currentCell=" + currentCell + ", currentRow=" + currentRow + "]";
    }
}
