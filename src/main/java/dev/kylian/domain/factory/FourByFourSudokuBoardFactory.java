package dev.kylian.domain.factory;

import dev.kylian.domain.composite.*;
import dev.kylian.domain.strategy.ValueStrategy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FourByFourSudokuBoardFactory implements SudokuBoardFactory {

    @Override
    public SudokuComponent createSudokuBoard(File file) {
        String line = "";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            line = bufferedReader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("line: " + line + " line.length(): " + line.length());

        if (line.length() != 16) {
            throw new IllegalArgumentException("Invalid line length. Expected length: 81");
        }

        List<SudokuComponent> components = new ArrayList<>();


        // Create row components
        for (int row = 0; row < 4; row++) {
            List<Cell> cells = new ArrayList<>();
            for (int col = 0; col < 4; col++) {
                int index = row * 4 + col;
                int value = Character.getNumericValue(line.charAt(index));
                boolean isGiven = (value != 0);
                Point point = new Point(col, row);
                int box = getBoxNumber(col, row);
//                System.out.println("row: " + row + " col: " + col + " box: " + box + " index: " + index + " value: " + value);
                Set<Integer> helpValues = new HashSet<>();
                boolean isCorrect = true;
                ValueStrategy valueStrategy = null;
                cells.add(new Cell(value, point, box, helpValues, isGiven, isCorrect, valueStrategy));
            }
//            if (test == null) test = new CellGroupComponent(cells);
            components.add(new CellGroupComponent(cells));
        }

        // Create column components
        for (int col = 0; col < 4; col++) {
            List<Cell> cells = new ArrayList<>();
            for (int row = 0; row < 4; row++) {
                int index = row * 4 + col;
                int value = Character.getNumericValue(line.charAt(index));
                boolean isGiven = (value != 0);
                Point point = new Point(col, row);
                int box = getBoxNumber(col, row);
//                System.out.println("row: " + row + " col: " + col + " box: " + box + " index: " + index + " value: " + value);
                Set<Integer> helpValues = new HashSet<>();
                boolean isCorrect = true;
                ValueStrategy valueStrategy = null;
                cells.add(new Cell(value, point, box, helpValues, isGiven, isCorrect, valueStrategy));
            }
//            if (test2 == null) test2 = new CellGroupComponent(cells);
            components.add(new CellGroupComponent(cells));
        }

        // Create box components
        for (int box = 0; box < 4; box++) {
            List<Cell> cells = new ArrayList<>();
            int startRow = box / 2 * 2;
            int startCol = box % 2 * 2;
            for (int row = startRow; row < startRow + 2; row++) {
                for (int col = startCol; col < startCol + 2; col++) {
                    int index = row * 4 + col;
                    int value = Character.getNumericValue(line.charAt(index));
                    boolean isGiven = (value != 0);
                    Point point = new Point(col, row);
                    int boxNumber = getBoxNumber(col, row);
                    Set<Integer> helpValues = new HashSet<>();
                    boolean isCorrect = true;
                    ValueStrategy valueStrategy = null;
                    cells.add(new Cell(value, point, boxNumber, helpValues, isGiven, isCorrect, valueStrategy));
                }
            }
            components.add(new CellGroupComponent(cells));
        }

        return new BoardComponent(components, 4);
    }

    private int getBoxNumber(int col, int row) {
        return (row / 2) * 2 + (col / 2);
    }
}
