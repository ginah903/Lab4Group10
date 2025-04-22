package hw4.game;

public class Game {
	private Grid grid;
	private Player player;
	
	//constructor to make grid
	public Game(Grid grid) {
		this.grid = grid;
	}
	
	//constructor to initialize with random grid size
	public Game(int gridSize) {
		this.grid = createRandomGrid(gridSize);
		this.player = new Player(grid.getRows().get(2), grid.getRows().get(2).getCells().get(2)); //default starting position at (2,2)
	}
	
	//GETTERS AND SETTERS
	public Grid getGrid() {
		return grid;
	}
	
	public void setGrid(Grid grid) {
		this.grid = grid;
	}
	
	//player movement
	public boolean play(Movement movement, Player player) {
		if(movement == null || player == null) {
			return false;
		}
		
		Row currentRow = player.getCurrentRow();
		Cell currentCell = player.getCurrentCell();
		
		switch(movement) {
			case UP:
				if(currentRow.getIndex() > 0 && currentCell.getUp() != CellComponents.WALL) {
					Row newRow = grid.getRows().get(currentRow.getIndex() - 1);
					Cell newCell = newRow.getCells().get(currentCell.getIndex());
					player.setCurrentRow(newRow);
					player.setCurrentCell(newCell);
					return true;
				}
				break;
			case RIGHT:
				if(currentCell.getRight() != CellComponents.WALL) {
					Cell newCell = currentRow.getCells().get(currentCell.getIndex() + 1);
					player.setCurrentCell(newCell);
					return true;
				}
				break;
			case DOWN:
				if(currentRow.getIndex() < grid.getRows().size() - 1 && currentCell.getDown() != CellComponents.WALL) {
					Row newRow = grid.getRows().get(currentRow.getIndex() + 1);
					Cell newCell = newRow.getCells().get(currentCell.getIndex());
					player.setCurrentRow(newRow);
					player.setCurrentCell(newCell);
					return true;
				}
				break;
			case LEFT:
				if(currentCell.getLeft() != CellComponents.WALL) {
					Cell newCell = currentRow.getCells().get(currentCell.getIndex() - 1);
					player.setCurrentCell(newCell);
					return true;
				}
				break;
		}
		return false;
	}
	
	//create a random grid between 3x3 and 7x7
	public Grid createRandomGrid(int size) {
		if(size < 3 || size > 7) {
			return null;
		}
		
		Grid randomGrid = new Grid(size);
		int randomExitRow = (int)(Math.random() * size);
		randomGrid.getRows().get(randomExitRow).getCells().get(0).setLeft(CellComponents.EXIT);
		return randomGrid;
	}
	
	/**
	 * Returns a string representation of the Game, lists out the grid.
	 */
	@Override
	public String toString() {
		return "Game [grid=" + grid = "]";
	}
}
