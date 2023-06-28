package dev.kylian.controller;

import dev.kylian.domain.SudokuGame;
import dev.kylian.domain.composite.BoardComponent;
import dev.kylian.domain.composite.Cell;
import dev.kylian.domain.composite.CellGroupComponent;
import dev.kylian.domain.factory.BoardFactory;
import dev.kylian.domain.strategy.ValueStrategy;
import dev.kylian.domain.visitor.CreateCellGridVisitor;
import dev.kylian.domain.visitor.Visitor;
import dev.kylian.ui.BoardView;

import java.io.PrintWriter;

/**
 * Controller for handling game logic and updating the view.
 */
public class GameController {
    private final SudokuGame game;
    private final BoardView boardView;
    private ValueStrategy valueStrategy;

    /**
     * Constructs a GameController with a specific game type.
     *
     * @param type the type of game
     */
    public GameController(String type, PrintWriter printWriter) {
        game = new SudokuGame(new BoardFactory());
        game.initializeNewGame(type);

        this.boardView = new BoardView(this, printWriter);
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
        game.getSudoku().setValueStrategy(valueStrategy);
        try {
            game.getSudoku().setValue(x, y, value);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        if (isWin()) {
            System.out.println("You won!");
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

    public void setValueStrategy(ValueStrategy valueStrategy) {
        this.valueStrategy = valueStrategy;
    }


    /**
     * Unfinished method for checking if all the cells are valid
     * @hidden
     */
    public void actionCheckForIncorrectValues() {
        game.getSudoku().accept(new Visitor() {
            @Override
            public void visit(Cell component) {
            }
            @Override
            public void visit(CellGroupComponent component) {
            }
            @Override
            public void visit(BoardComponent component) {
            }
        });
    }
}
