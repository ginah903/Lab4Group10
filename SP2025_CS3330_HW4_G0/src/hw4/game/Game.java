package hw4.game;

import java.util.ArrayList;

import hw4.maze.*;
import hw4.player.*;

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
	    if (movement == null || player == null) {
	        return false;
	    }

	    Row currentRow = player.getCurrentRow();
	    Cell currentCell = player.getCurrentCell();

	    int currentRowIndex = grid.getRows().indexOf(currentRow);
	    int currentCellIndex = currentRow.getCells().indexOf(currentCell);

	    switch (movement) {
	        case UP:
	            if (currentRowIndex > 0 && currentCell.getUp() != CellComponents.WALL) {
	                Row newRow = grid.getRows().get(currentRowIndex - 1);
	                Cell newCell = newRow.getCells().get(currentCellIndex);
	                player.setCurrentRow(newRow);
	                player.setCurrentCell(newCell);
	                return true;
	            }
	            break;

	        case RIGHT:
	            if (currentCellIndex < currentRow.getCells().size() - 1 &&
	                currentCell.getRight() != CellComponents.WALL) {
	                Cell newCell = currentRow.getCells().get(currentCellIndex + 1);
	                player.setCurrentCell(newCell);
	                return true;
	            }
	            break;

	        case DOWN:
	            if (currentRowIndex < grid.getRows().size() - 1 &&
	                currentCell.getDown() != CellComponents.WALL) {
	                Row newRow = grid.getRows().get(currentRowIndex + 1);
	                Cell newCell = newRow.getCells().get(currentCellIndex);
	                player.setCurrentRow(newRow);
	                player.setCurrentCell(newCell);
	                return true;
	            }
	            break;

	        case LEFT:
	            // Check for exit escape
	            if (currentCellIndex == 0 && currentCell.getLeft() == CellComponents.EXIT) {
	                System.out.println("🎉 Agent escaped through the EXIT!");
	                return true;
	            }

	            if (currentCellIndex > 0 && currentCell.getLeft() != CellComponents.WALL) {
	                Cell newCell = currentRow.getCells().get(currentCellIndex - 1);
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
		
		ArrayList<Row> rows = new ArrayList<>();
		
		for (int i = 0; i <size; i++) {
			ArrayList<Cell> cells = new ArrayList<>();
			for (int j = 0; j < size; j++) {
				Cell cell = new Cell(
					CellComponents.WALL,
					CellComponents.WALL,
					CellComponents.WALL,
					CellComponents.WALL
				);
				cells.add(cell);		
			}
			Row row = new Row(cells);
			rows.add(row);
		}
		
		int randomExitRow = (int)(Math.random() * size);
		rows.get(randomExitRow).getCells().get(0).setLeft(CellComponents.EXIT);
		
		return new Grid(rows);
	}
	
	/**
	 * Returns a string representation of the Game, lists out the grid.
	 */
	@Override
	public String toString() {
		return "Game [grid=" + grid + "]";
	}
	
	//helper method providing grid updates and game status
	private static void printGrid(Game game) {
        Grid grid = game.getGrid();
        Player player = game.player;

        for (Row row : grid.getRows()) {
            for (Cell cell : row.getCells()) {
                if (player.getCurrentRow() == row && player.getCurrentCell() == cell) {
                    System.out.print("A");
                } else if (cell.getLeft() == CellComponents.EXIT) {
                    System.out.print("E");
                } else {
                    System.out.print("S");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Main method to run a test simulation of the application
     */
    public static void main(String[] args) {
        Game game = new Game(3);

        System.out.println("Starting Grid:");
        printGrid(game);

        Movement[] moves = {
            Movement.UP,
            Movement.LEFT,
            Movement.LEFT,
            Movement.DOWN,
            Movement.RIGHT,
            Movement.UP,
            Movement.LEFT
        };

        for (Movement move : moves) {
            System.out.println("Attempting agent move: " + move);
            boolean result = game.play(move, game.player);

            if (result) {
                System.out.println("Agent moved successfully!.");
            } else {
                System.out.println("Agent was unable to move and got stuck.");
            }

            printGrid(game);
        }

        System.out.println("Gameplay test complete!");
    }
	
	
}


