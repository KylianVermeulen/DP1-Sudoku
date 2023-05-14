package dev.kylian.controller;

import dev.kylian.domain.SudokuGame;
import dev.kylian.domain.composite.Cell;
import dev.kylian.domain.visitor.CreateCellGridVisitor;
import dev.kylian.ui.TerminalView;

import java.io.PrintWriter;

public class MainController {
    private final TerminalView terminalView = new TerminalView(new PrintWriter(System.out));
    private SudokuGame game;

    public MainController() {
        game = new SudokuGame();
        game.initializeGame("samurai");
        printBoard();
        game.getSudoku().setValue(10, 6, 1);
        printBoard();

//        game.getSudoku().setValue(4, 1, 1);
//        printWriter.println("Completed: \u001B[31m" + game.getSudoku().isValid() + "\u001B[0m");
//        printWriter.flush();
    }

    void printBoard() {
        CreateCellGridVisitor visitor = new CreateCellGridVisitor();
        game.getSudoku().accept(visitor);
        Cell[][] grid = visitor.getGrid();
        terminalView.printSudokuBoard(grid);
    }
}
