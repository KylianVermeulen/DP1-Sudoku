package dev.kylian;

import dev.kylian.domain.SudokuGame;
import dev.kylian.domain.composite.Cell;
import dev.kylian.domain.visitor.CreateCellGridVisitor;
import dev.kylian.ui.TerminalView;

import java.io.PrintWriter;

public class App {

    public static void main(String[] args) {
        PrintWriter printWriter = new PrintWriter(System.out);
        TerminalView terminalView = new TerminalView(printWriter);

        SudokuGame game = new SudokuGame();
        game.initializeGame("samurai");

        CreateCellGridVisitor visitor = new CreateCellGridVisitor();
        game.getSudoku().accept(visitor);

        Cell[][] grid = visitor.getGrid();

        terminalView.printSudokuBoard(grid);
        printWriter.println("Completed: \u001B[31m" + game.getSudoku().isValid() + "\u001B[0m");
        printWriter.flush();

//        game.getSudoku().setValue(4, 1, 1);
        game.getSudoku().setValue(10, 6, 1);

        terminalView.printSudokuBoard(grid);
        printWriter.println("Completed: \u001B[31m" + game.getSudoku().isValid() + "\u001B[0m");
        printWriter.flush();
    }
}
