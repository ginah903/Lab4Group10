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
		
		//randomg grid generator with internal error checking
		ArrayList<Row> rows = new ArrayList<>();
		Random rand = new Random();
		
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
	 * @return
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
	 * @return
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


