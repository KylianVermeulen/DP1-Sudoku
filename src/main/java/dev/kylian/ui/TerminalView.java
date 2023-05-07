package dev.kylian.ui;

import dev.kylian.domain.SudokuComponent;

import java.io.PrintWriter;

public class TerminalView {
    private final PrintWriter printWriter;

    public TerminalView(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }

    public void viewBoard(SudokuComponent board) {
        printWriter.println(board);
        printWriter.flush();
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
