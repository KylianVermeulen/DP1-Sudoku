package dev.kylian;

import dev.kylian.domain.Board;
import dev.kylian.domain.Cell;
import dev.kylian.domain.SudokuComponent;
import dev.kylian.domain.UniqueValueComponent;
import dev.kylian.ui.TerminalView;

import java.io.PrintWriter;
import java.util.List;

public class App {

    public static void main(String[] args) {
        PrintWriter printWriter = new PrintWriter(System.out);
        TerminalView terminalView = new TerminalView(printWriter);
        terminalView.viewSudoku();

        Cell tl1 = new Cell(0, 0, 0);
        Cell tl2 = new Cell(1, 0, 3);
        Cell tl3 = new Cell(0, 1, 4);
        Cell tl4 = new Cell(1, 1, 0);

        Cell tr1 = new Cell(2, 0, 4);
        Cell tr2 = new Cell(3, 0, 0);
        Cell tr3 = new Cell(2, 1, 0);
        Cell tr4 = new Cell(3, 1, 2);

        Cell bl1 = new Cell(0, 2, 1);
        Cell bl2 = new Cell(1, 2, 0);
        Cell bl3 = new Cell(0, 3, 0);
        Cell bl4 = new Cell(1, 3, 2);

        Cell br1 = new Cell(2, 2, 0);
        Cell br2 = new Cell(3, 2, 3);
        Cell br3 = new Cell(2, 3, 1);
        Cell br4 = new Cell(3, 3, 0);

        Cell[] tl = {tl1, tl2, tl3, tl4};
        Cell[] tr = {tr1, tr2, tr3, tr4};
        Cell[] bl = {bl1, bl2, bl3, bl4};
        Cell[] br = {br1, br2, br3, br4};

        SudokuComponent topLeft = new UniqueValueComponent(tl);
        SudokuComponent topRight = new UniqueValueComponent(tr);
        SudokuComponent bottomLeft = new UniqueValueComponent(bl);
        SudokuComponent bottomRight = new UniqueValueComponent(br);

        SudokuComponent topRow = new UniqueValueComponent(new Cell[]{tl1, tl2, tr1, tr2});
        SudokuComponent secondRow = new UniqueValueComponent(new Cell[]{tl3, tl4, tr3, tr4});
        SudokuComponent thirdRow = new UniqueValueComponent(new Cell[]{bl1, bl2, br1, br2});
        SudokuComponent bottomRow = new UniqueValueComponent(new Cell[]{bl3, bl4, br3, br4});

        SudokuComponent leftColumn = new UniqueValueComponent(new Cell[]{tl1, tl3, bl1, bl3});
        SudokuComponent secondColumn = new UniqueValueComponent(new Cell[]{tl2, tl4, bl2, bl4});
        SudokuComponent thirdColumn = new UniqueValueComponent(new Cell[]{tr1, tr3, br1, br3});
        SudokuComponent rightColumn = new UniqueValueComponent(new Cell[]{tr2, tr4, br2, br4});

        SudokuComponent[] components = {topLeft, topRight, bottomLeft, bottomRight, topRow, secondRow, thirdRow, bottomRow, leftColumn, secondColumn, thirdColumn, rightColumn};
        SudokuComponent board = new Board(List.of(components), 4);

        printWriter.print("\u001B[0m");

        terminalView.viewBoard(board);

        if (board.isValid()) printWriter.println("\033[1;32mBoard Valid\u001B[0m");
        else printWriter.println("\033[1;31mBoard Invalid\u001B[0m");
        printWriter.flush();

        board.setValue(0, 0, 2);
        board.setValue(1, 1, 1);

        if (topLeft.isValid()) printWriter.println("\033[1;32mTopLeft Valid\u001B[0m");
        else printWriter.println("\033[1;31mTopLeft Invalid\u001B[0m");
        printWriter.flush();

        if (leftColumn.isValid()) printWriter.println("\033[1;32mLeftColumn Valid\u001B[0m");
        else printWriter.println("\033[1;31mLeftColumn Invalid\u001B[0m");
        printWriter.flush();

        board.setValue(0, 0, 3);
        board.isValid();

        terminalView.viewBoard(board);
    }
}
