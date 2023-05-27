package dev.kylian.controller;

import dev.kylian.domain.SudokuGame;
import dev.kylian.domain.composite.Cell;
import dev.kylian.domain.visitor.CreateCellGridVisitor;
import dev.kylian.ui.TerminalView;

import java.io.PrintWriter;

public class GameController {
    private final TerminalView terminalView = new TerminalView(new PrintWriter(System.out));
    private SudokuGame game;

    public GameController(String type) {
        game = new SudokuGame();
        game.initializeNewGame(type);
        printBoard();

//        PrintWriter printWriter = new PrintWriter(System.out);
//        printWriter.println("Completed: \u001B[31m" + game.getSudoku().isValid() + "\u001B[0m");
//        printWriter.flush();
    }

    void printBoard() {
        CreateCellGridVisitor visitor = new CreateCellGridVisitor();
        game.getSudoku().accept(visitor);
        Cell[][] grid = visitor.getGrid();
        terminalView.printSudokuBoard(grid, visitor.getBoxSize());
    }
}
