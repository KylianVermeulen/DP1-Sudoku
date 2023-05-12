package dev.kylian;

import dev.kylian.domain.composite.Cell;
import dev.kylian.ui.TerminalView;

import java.io.PrintWriter;

public class App {

    public static void main(String[] args) {
        PrintWriter printWriter = new PrintWriter(System.out);

        String sudokuLine = "700509001000000000150070063003904100000050000002106400390040076000000000600201004";

        Cell[][] grid = App.parseSudokuLine(sudokuLine);
        TerminalView.printSudokuBoard(grid, printWriter);
        printWriter.flush();
    }

    public static Cell[][] parseSudokuLine(String line) {
        int size = 9;
        Cell[][] grid = new Cell[size][size];

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                char ch = line.charAt(row * size + col);
                int value = Character.getNumericValue(ch);
                boolean isGiven = value != 0;

                grid[row][col] = new Cell(value, isGiven);
            }
        }

        return grid;
    }
}
