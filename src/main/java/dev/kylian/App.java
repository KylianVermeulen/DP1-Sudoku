package dev.kylian;

import dev.kylian.domain.SudokuGame;
import dev.kylian.domain.composite.Cell;
import dev.kylian.domain.composite.SudokuComponent;
import dev.kylian.domain.factory.BasicSudokuBoardFactory;
import dev.kylian.domain.factory.SudokuBoardFactory;
import dev.kylian.domain.visitor.CreateCellGridVisitor;
import dev.kylian.ui.TerminalView;

import java.io.PrintWriter;

public class App {

    public static void main(String[] args) {
        PrintWriter printWriter = new PrintWriter(System.out);

        SudokuGame game = new SudokuGame();
        game.initializeGame("basic");

        CreateCellGridVisitor visitor = new CreateCellGridVisitor();
        game.getSudoku().accept(visitor);

        Cell[][] grid = visitor.getGrid();

        TerminalView.printSudokuBoard(grid, printWriter);
        printWriter.flush();
    }
}
