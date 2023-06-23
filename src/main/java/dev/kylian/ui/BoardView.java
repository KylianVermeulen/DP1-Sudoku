package dev.kylian.ui;

import dev.kylian.controller.GameController;
import dev.kylian.domain.composite.Cell;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class BoardView {
    private final GameController gameController;
    private final PrintWriter printWriter;
    private Cell[][] grid;
    private int boxSize;
    private int currentX = 0;
    private int currentY = 0;

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
            if (input.matches("\\d+")) {
                // Numeric input, place the value in the cell.
                gameController.actionPlaceValue(currentX, currentY, Integer.parseInt(input));
            } else {
                // Direction input, update the current cell.
                updateCurrentCell(input);
                printWriter.println("\033[H\033[2J");
                printWriter.flush();
                printBoard();
                printWriter.flush();
            }
        }
    }

    private void updateCurrentCell(String direction) {
        switch (direction.toLowerCase()) {
            case "w" -> currentY = Math.max(0, currentY - 1);
            case "s" -> currentY = Math.min(grid.length - 1, currentY + 1);
            case "a" -> currentX = Math.max(0, currentX - 1);
            case "d" -> currentX = Math.min(grid[0].length - 1, currentX + 1);
            default -> {
                printWriter.println("Invalid input. Please enter 'w', 's', 'a', 'd' or a number.");
                printWriter.flush();
            }
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
                boolean isSelected = row == currentY && col == currentX;

                // Print cell value
                if (value == 0) {
//                    printWriter.print(". ");
                    printWriter.print(isSelected ? "\u001B[36m. \u001B[0m" : ". ");
                } else {
                    if (cell.isGiven())
                        printWriter.print((isSelected ? "\u001B[36m" : "\u001B[32m") + value + "\u001B[0m ");
                    else if (!cell.isCorrect())
                        printWriter.print((isSelected ? "\u001B[36m" : "\u001B[33m") + value + "\u001B[0m ");
                    else if (cell.isValid())
                        printWriter.print((isSelected ? "\u001B[36m" : "\u001B[34m") + value + "\u001B[0m ");
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
