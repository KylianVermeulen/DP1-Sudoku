package dev.kylian.controller;

import dev.kylian.domain.SudokuGame;
import dev.kylian.domain.composite.Cell;
import dev.kylian.domain.composite.Point;
import dev.kylian.domain.visitor.CreateCellGridVisitor;
import dev.kylian.ui.BoardView;

public class GameController {
    private final SudokuGame game;
    private final BoardView boardView;

    public GameController(String type) {
        game = new SudokuGame();
        game.initializeNewGame(type);

        this.boardView = new BoardView(this);
        actionViewBoard();
    }

    public void actionViewBoard() {
        CreateCellGridVisitor visitor = new CreateCellGridVisitor();
        game.getSudoku().accept(visitor);
        Cell[][] grid = visitor.getGrid();
        int boxSize = visitor.getBoxSize();
        boardView.setGrid(grid);
        boardView.setBoxSize(boxSize);
        boardView.render();
    }

    public void actionPlaceValue(int x, int y, int value) {
        game.getSudoku().setValue(x, y, value);
        actionViewBoard();
    }
}
