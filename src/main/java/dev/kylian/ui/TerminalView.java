package dev.kylian.ui;

import dev.kylian.domain.composite.Cell;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;

public class TerminalView {
    private final PrintWriter printWriter;

    public TerminalView(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }

    public void printSudokuBoard(Cell[][] grid) {
        printWriter.println("\033[H\033[2J");
        printWriter.flush();

        int size = grid.length;
        int size2 = Arrays.stream(grid).max(Comparator.comparingInt(o -> o.length)).orElseThrow().length;

        // Print horizontal line
        printHorizontalLine(size);

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size2; col++) {
                Cell cell = grid[row][col];

                // Print vertical separator
                if (col % 3 == 0) {
                    printWriter.print("| ");
                } else {
                    printWriter.print("  ");
                }

                if (cell == null) {
                    printWriter.print("  ");
                    continue;
                }
                int value = cell.getValue();

                // Print cell value
                if (value == 0) {
                    printWriter.print(". ");
                } else {
                    if (cell.isGiven())
                        printWriter.print("\u001B[32m" + value + "\u001B[0m ");
                    else if (!cell.isCorrect())
                        printWriter.print("\u001B[33m" + value + "\u001B[0m ");
                    else if (cell.isValid())
                        printWriter.print("\u001B[34m" + value + "\u001B[0m ");
//                    printWriter.print("\u001B[38;5;231;48;5;21m" + value + "\u001B[0m ");
//                    https://stackoverflow.com/questions/4842424/list-of-ansi-color-escape-sequences
                }
            }

            printWriter.println("|"); // End of row

            // Print horizontal line
            if ((row + 1) % 3 == 0) {
                printHorizontalLine(size);
            }
        }

        printWriter.flush();
    }

    private void printHorizontalLine(int size) {
        int lineLength = size * 4 + size / 6;

        for (int i = 0; i < lineLength; i++) {
            printWriter.print("-");
        }

        printWriter.println();
    }
}
