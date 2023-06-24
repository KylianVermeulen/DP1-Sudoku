package dev.kylian.ui;

import dev.kylian.domain.composite.Cell;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;

public class HelpNumberBoardView {
    private static final String RESET = "\u001B[0m";
    private static final String CYAN = "\u001B[36m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";

    private final PrintWriter printWriter;
    private Cell[][] grid;
    private int boxSize;
    private int currentX = 0;
    private int currentY = 0;

    public HelpNumberBoardView(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }

    public void render() {
        this.printBoard();
    }

    private void printBoard() {
        int rowSize = (boxSize == 9) ? 3 : (boxSize == 4) ? 2 : 3;
        int colSize = (boxSize == 9) ? 3 : 2;

        int sizeY = grid.length;
        int sizeX = Arrays.stream(grid).max(Comparator.comparingInt(o -> o.length)).orElseThrow().length;

        printWriter.println("sizeY: " + sizeY);
        printWriter.println("rowSize: " + rowSize);
        printWriter.println("sizeX: " + sizeX);
        printWriter.println("colSize: " + colSize);

        // Print horizontal line
        printHorizontalLine(sizeX);

        for (int row = 0; row < sizeY; row++) {
            for (int col = 0; col < sizeX; col++) {
                Cell cell = grid[row][col];

                // Print vertical separator
                if (col % colSize == 0) {
                    printWriter.print("| ");
                } else {
                    printWriter.print("  ");
                }

                if (cell == null) {
                    printWriter.print("  ");
                    continue;
                }

                int value = cell.getValue();
                Set<Integer> helpValues = cell.getHelpValues();
                boolean isSelected = row == currentY && col == currentX;

                // Print cell
                if (value == 0) {
                    if (helpValues.size() > 0) {
                        printWriter.print((isSelected ? CYAN : BLUE) + helpValues + RESET + " ");
                    } else {
                        printWriter.print(isSelected ? CYAN + ". " + RESET : ". ");
                    }
                } else {
                    if (cell.isGiven())
                        printWriter.print((isSelected ? CYAN : GREEN) + value + RESET + " ");
                    else if (!cell.isCorrect())
                        printWriter.print((isSelected ? CYAN : YELLOW) + value + RESET + " ");
                    else if (cell.isValid())
                        printWriter.print((isSelected ? CYAN : BLUE) + value + RESET + " ");
                }
            }

            printWriter.println("|"); // End of row

            // Print horizontal line
            if ((row + 1) % rowSize == 0) {
                printHorizontalLine(sizeX);
            }
        }
    }

    private void printHorizontalLine(int sizeX) {
        // Calculate line length: each cell takes 3 characters,
        // each vertical separator 1 character, there are sizeX times vertical separators + 1
        int lineLength = sizeX * 3 + sizeX + 1;

        for (int i = 0; i < lineLength; i++) {
            printWriter.print("-");
        }

        printWriter.println();
    }

    public void setGrid(Cell[][] grid) {
        this.grid = grid;
    }

    public void setBoxSize(int boxSize) {
        this.boxSize = boxSize;
    }

    public void setCurrentX(int currentX) {
        this.currentX = currentX;
    }

    public void setCurrentY(int currentY) {
        this.currentY = currentY;
    }
}
