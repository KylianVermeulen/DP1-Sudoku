package dev.kylian.ui;

import dev.kylian.controller.GameController;
import dev.kylian.domain.composite.Cell;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class BoardView {
    private final GameController gameController;
    PrintWriter printWriter;
    private Cell[][] grid;
    private int boxSize;

    public BoardView(GameController gameController) {
        this.gameController = gameController;
        this.printWriter = new PrintWriter(System.out);
    }

    public void render() {
        printWriter.println("\033[H\033[2J");
        printWriter.flush();
        printBoard();
        printWriter.flush();

        Scanner scanner = new Scanner(System.in);
        String input;
        while (scanner.hasNextLine()) {
            input = scanner.nextLine();
            gameController.actionPlaceValue(Integer.parseInt(input.substring(0, 1)), Integer.parseInt(input.substring(1, 2)), Integer.parseInt(input.substring(2, 3)));
        }
    }

    private void printBoard() {
        int rowSize = (boxSize == 9) ? 3 : (boxSize == 4) ? 2 : 3;
        int colSize = (boxSize == 9) ? 3 : 2;

        int size = grid.length;
        int size2 = Arrays.stream(grid).max(Comparator.comparingInt(o -> o.length)).orElseThrow().length;

        // Print horizontal line
        printHorizontalLine(size);

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size2; col++) {
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
                }
            }

            printWriter.println("|"); // End of row

            // Print horizontal line
            if ((row + 1) % rowSize == 0) {
                printHorizontalLine(size);
            }
        }
    }

    private void printHorizontalLine(int size) {
        int lineLength = size * 4 + size / 6;

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
}
