package hw4.game;

import java.util.ArrayList;
import java.util.Random;

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
	
	// GETTERS AND SETTERS
	public Grid getGrid() {
		return grid;
	}
	
	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	/**
	 * Moves the provided player in the desired direction, returns true or false if successful. 
	 * @param movement
	 * @param player
	 * @return Returns true if movement is successful, false if not.
	 */
	public boolean play(Movement movement, Player player) {
	    if (movement == null || player == null) {
	        return false;
	    }

	    // resets indexes to -1
	    int currentRowIndex = -1;
	    int currentCellIndex = -1;

	    for (int i = 0; i < grid.getRows().size(); i++) {
	        if (grid.getRows().get(i) == player.getCurrentRow()) {
	            currentRowIndex = i;
	            ArrayList<Cell> cells = grid.getRows().get(i).getCells();
	            for (int j = 0; j < cells.size(); j++) {
	                if (cells.get(j) == player.getCurrentCell()) {
	                    currentCellIndex = j;
	                    break;
	                }
	            }
	            break;
	        }
	    }

	    if (currentRowIndex == -1 || currentCellIndex == -1) {
	        return false; // Could not locate player
	    }

	    Row currentRow = grid.getRows().get(currentRowIndex);
	    Cell currentCell = currentRow.getCells().get(currentCellIndex);

	    switch (movement) {
	        case UP:
	            if (currentRowIndex > 0 && currentCell.getUp() == CellComponents.APERTURE) {
	                Row newRow = grid.getRows().get(currentRowIndex - 1);
	                Cell newCell = newRow.getCells().get(currentCellIndex);
	                player.setCurrentRow(newRow);
	                player.setCurrentCell(newCell);
	                return true;
	            }
	            break;

	        case RIGHT:
	            if (currentCellIndex < currentRow.getCells().size() - 1 &&
	                currentCell.getRight() == CellComponents.APERTURE) {
	                Cell newCell = currentRow.getCells().get(currentCellIndex + 1);
	                player.setCurrentCell(newCell);
	                return true;
	            }
	            break;

	        case DOWN:
	            if (currentRowIndex < grid.getRows().size() - 1 &&
	                currentCell.getDown() == CellComponents.APERTURE) {
	                Row newRow = grid.getRows().get(currentRowIndex + 1);
	                Cell newCell = newRow.getCells().get(currentCellIndex);
	                player.setCurrentRow(newRow);
	                player.setCurrentCell(newCell);
	                return true;
	            }
	            break;

	        case LEFT:
	            if (currentCellIndex == 0 && currentCell.getLeft() == CellComponents.EXIT) {
	                return true;
	            }
	            if (currentCellIndex > 0 && currentCell.getLeft() == CellComponents.APERTURE) {
	                Cell newCell = currentRow.getCells().get(currentCellIndex - 1);
	                player.setCurrentCell(newCell);
	                return true;
	            }
	            break;
	    }

	    return false;
	}

	
	/**
	 * Create a random grid between 3x3 and 7x7, return the Grid
	 * @param size The desired size
	 * @return The created grid
	 */
	public Grid createRandomGrid(int size) {
		if(size < 3 || size > 7) {
			return null;
		}
		
		//random grid generator with internal error checking
		ArrayList<Row> rows = new ArrayList<>();
		Random rand = new Random(202); // use fixed seed 
		
		int exitRow = rand.nextInt(size);
		
		
		for (int i = 0; i <size; i++) {
			ArrayList<Cell> cells = new ArrayList<>();
			for (int j = 0; j < size; j++) {
					CellComponents left = CellComponents.WALL;
					CellComponents right = CellComponents.WALL;
					CellComponents up = CellComponents.WALL;
					CellComponents down = CellComponents.WALL;
			
			//begin checking to see that all adjacent apertures match to each other
			if (j == 0) {
				left = (i == exitRow) ? CellComponents.EXIT : randomApertureOrWall(rand);
			}
			
			else {
				left = cells.get(j-1).getRight();
			}
			
			if (i > 0) {
				up = rows.get(i-1).getCells().get(j).getDown();
			}
			
			if (j < size - 1) {
                right = randomApertureOrWall(rand);
            }

            if (i < size - 1) {
                down = randomApertureOrWall(rand);
            }

            //establishes error checking process for each cell
            Cell newCell = new Cell(left, right, up, down);

            //error/validity checking for at least one and no fully open cells (all apertures)
            if (!hasAtLeastOneAperture(newCell) || hasAllApertures(newCell)) {
                //if there is no aperture, then goes back and adds one randomly 
                int side = rand.nextInt(4);
                switch (side) {
                    case 0: newCell.setLeft(CellComponents.APERTURE); break;
                    case 1: newCell.setRight(CellComponents.APERTURE); break;
                    case 2: newCell.setUp(CellComponents.APERTURE); break;
                    case 3: newCell.setDown(CellComponents.APERTURE); break;
                }
            }

            cells.add(newCell);
			}
        rows.add(new Row(cells));
    }

		return new Grid(rows);
	}

	/**
	 * Returns either random generation of either aperture or wall
	 * @param rand
	 * @return boolean
	 */
	private CellComponents randomApertureOrWall(Random rand) {
	    return rand.nextBoolean() ? CellComponents.APERTURE : CellComponents.WALL;
	}
	
	/**
	 * method to ensure that each cell has at least on aperture, no landlocked cells
	 * @param cell
	 * @return Returns true if there is at least one aperture.
	 */
	private boolean hasAtLeastOneAperture(Cell cell) {
	    return cell.getLeft() == CellComponents.APERTURE ||
	           cell.getRight() == CellComponents.APERTURE ||
	           cell.getUp() == CellComponents.APERTURE ||
	           cell.getDown() == CellComponents.APERTURE;
	}
	
	/**
	 * Method to ensure no cell contains only apertures, ensure some walls are included
	 * @param cell
	 * @return Returns true if cell has all apertures
	 */
	private boolean hasAllApertures(Cell cell) {
	    return cell.getLeft() == CellComponents.APERTURE &&
	           cell.getRight() == CellComponents.APERTURE &&
	           cell.getUp() == CellComponents.APERTURE &&
	           cell.getDown() == CellComponents.APERTURE;
	}
	
	/**
	 * Returns a string representation of the Game, lists out the grid.
	 */
	@Override
	public String toString() {
		return "Game [grid=" + grid + "]";
	}
	
	/**
	 * helper method that prints live updates of grid maze
	 */
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