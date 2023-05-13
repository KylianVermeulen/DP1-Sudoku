package dev.kylian.ui;

import dev.kylian.domain.composite.Cell;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;

public class TerminalView {
    private final PrintWriter printWriter;

    public TerminalView(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }

    public static void printSudokuBoard(Cell[][] grid, PrintWriter writer) {
        int size = grid.length;
        int size2 = Arrays.stream(grid).max(Comparator.comparingInt(o -> o.length)).orElseThrow().length;

        // Print horizontal line
        printHorizontalLine(size, writer);

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size2; col++) {
                Cell cell = grid[row][col];
                if (cell == null) {
                    continue;
                }
                int value = cell.getValue();

                // Print vertical separator
                if (col % 3 == 0) {
                    writer.print("| ");
                } else {
                    writer.print("  ");
                }

                // Print cell value
                if (value == 0) {
                    writer.print(". ");
                } else {
                    writer.print(value + " ");
                }
            }

            writer.println("|"); // End of row

            // Print horizontal line
            if ((row + 1) % 3 == 0) {
                printHorizontalLine(size, writer);
            }
        }
    }

    private static void printHorizontalLine(int size, PrintWriter writer) {
        int lineLength = size * 4 + size / 6;

        for (int i = 0; i < lineLength; i++) {
            writer.print("-");
        }

        writer.println();
    }

    public void viewSudoku() {
        String bgColor = "\u001B[48;5;";
        String cellColor = bgColor + "208m";
        String resetColor = "\u001B[0m";

        printWriter.print("\033[1;31m");
        printWriter.println("+-------+-------+-------+");
        printWriter.println("| 2 6   |    1  |       |");
        printWriter.println("| 3     |   5   |   8   |");
        printWriter.println("|       |   7   | 9     |");
        printWriter.println("+-------+-------+-------+");
        printWriter.println("| 5     |       | 1 9   |");
        printWriter.println("|       |       |       |");
        printWriter.println("| 9 8   |       |   6   |");
        printWriter.println("+-------+-------+-------+");
        printWriter.println("|   2 0 | 1     |       |");
        printWriter.println("| 7     | 8     | 3     |");
        printWriter.println("|       | 6     | 7 2   |");
        printWriter.println("+-------+-------+-------+");
        printWriter.flush();

        printWriter.print("\033[1;32m");
        printWriter.println("+-------+-------+-------+");
        printWriter.println("| 2     |    1  |       |");
        printWriter.println("| 5     |   5   |   8   |");
        printWriter.println("|       |   7   | 9     |");
        printWriter.println("+-------+-------+-------+");
        printWriter.println("| 5     |       | 1 9   |");
        printWriter.println("|       |       |       |");
        printWriter.println("| 9 8   |       |   6   |");
        printWriter.println("+-------+-------+-------+");
        printWriter.println("|   2 0 | 1     |       |");
        printWriter.println("| 7     | 8     | 3     |");
        printWriter.println("|       | 6     | 7 2   |");
        printWriter.println("+-------+-------+-------+");
        printWriter.flush();
    }
}
