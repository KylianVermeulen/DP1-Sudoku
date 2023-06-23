package dev.kylian.controller;

import dev.kylian.domain.SudokuGame;
import dev.kylian.domain.composite.Cell;
import dev.kylian.domain.visitor.CreateCellGridVisitor;
import dev.kylian.ui.BoardView;

/**
 * Controller for handling game logic and updating the view.
 */
public class GameController {
    private final SudokuGame game;
    private final BoardView boardView;

    /**
     * Constructs a GameController with a specific game type.
     *
     * @param type the type of game
     */
    public GameController(String type) {
        game = new SudokuGame();
        game.initializeNewGame(type);

        this.boardView = new BoardView(this);
        viewUpdatedBoard();
    }

    /**
     * Updates the board view based on the current state of the game.
     */
    public void viewUpdatedBoard() {
        CreateCellGridVisitor visitor = new CreateCellGridVisitor();
        game.getSudoku().accept(visitor);
        Cell[][] grid = visitor.getGrid();
        int boxSize = visitor.getBoxSize();
        boardView.setGrid(grid);
        boardView.setBoxSize(boxSize);
        boardView.render();
    }

    /**
     * Places a value at a specific position on the board and checks for a win.
     *
     * @param x     the x coordinate
     * @param y     the y coordinate
     * @param value the value to be placed
     */
    public void actionPlaceValue(int x, int y, int value) {
        game.getSudoku().setValue(x, y, value);
        if (isWin()) {
            System.out.println("You win!");
            System.exit(0);
        }
        viewUpdatedBoard();
    }

    /**
     * Checks if the game state is valid.
     *
     * @return true if valid, false otherwise
     */
    public boolean isWin() {
        return game.getSudoku().isValid();
    }
}
