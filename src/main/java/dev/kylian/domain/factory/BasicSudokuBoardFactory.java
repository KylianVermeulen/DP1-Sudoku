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

public class BasicSudokuBoardFactory implements SudokuBoardFactory {

    @Override
    public SudokuComponent createSudokuBoard(File file) {
        String line = "";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            line = bufferedReader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("line: " + line + " line.length(): " + line.length());

        if (line.length() != 81) {
            throw new IllegalArgumentException("Invalid line length. Expected length: 81");
        }

        List<SudokuComponent> components = new ArrayList<>();

        // Create row components
        for (int row = 0; row < 9; row++) {
            List<Cell> cells = new ArrayList<>();
            for (int col = 0; col < 9; col++) {
                int index = row * 9 + col;
                int value = Character.getNumericValue(line.charAt(index));
                boolean isGiven = (value != 0);
                Point point = new Point(col, row);
                int box = getBoxNumber(col, row);
                Set<Integer> helpValues = new HashSet<>();
                boolean isCorrect = true;
                ValueStrategy valueStrategy = null;
                cells.add(new Cell(value, point, box, helpValues, isGiven, isCorrect, valueStrategy));
            }
            components.add(new CellGroupComponent(cells));
        }

        // Create column components
        for (int col = 0; col < 9; col++) {
            List<Cell> cells = new ArrayList<>();
            for (int row = 0; row < 9; row++) {
                int index = row * 9 + col;
                int value = Character.getNumericValue(line.charAt(index));
                boolean isGiven = (value != 0);
                Point point = new Point(col, row);
                int box = getBoxNumber(col, row);
                Set<Integer> helpValues = new HashSet<>();
                boolean isCorrect = true;
                ValueStrategy valueStrategy = null;
                cells.add(new Cell(value, point, box, helpValues, isGiven, isCorrect, valueStrategy));
            }
            components.add(new CellGroupComponent(cells));
        }

        // Create box components
        for (int box = 0; box < 9; box++) {
            List<Cell> cells = new ArrayList<>();
            int startRow = box / 3 * 3;
            int startCol = box % 3 * 3;
            for (int row = startRow; row < startRow + 3; row++) {
                for (int col = startCol; col < startCol + 3; col++) {
                    int index = row * 9 + col;
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

        return new BoardComponent(components, 9);
    }

    private int getBoxNumber(int col, int row) {
        return (row / 3) * 3 + (col / 3);
    }
}
