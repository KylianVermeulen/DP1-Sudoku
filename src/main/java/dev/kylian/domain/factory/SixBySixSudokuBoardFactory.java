package dev.kylian.domain.factory;

import dev.kylian.domain.SudokuFileReader;
import dev.kylian.domain.composite.*;
import dev.kylian.domain.strategy.ValueStrategy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SixBySixSudokuBoardFactory implements SudokuBoardFactory {
    private final SudokuFileReader reader;

    public SixBySixSudokuBoardFactory(SudokuFileReader reader) {
        this.reader = reader;
    }

    @Override
    public SudokuComponent createSudokuBoard(File file) {
        String line;

        try {
            line = reader.readFileToString(file);
        } catch (IOException e) {
            throw new RuntimeException("Error reading file", e);
        }

        System.out.println("line: " + line + " line.length(): " + line.length());

        if (line.length() != 36) {
            throw new IllegalArgumentException("Invalid line length. Expected length: 36");
        }

        List<SudokuComponent> components = new ArrayList<>();

        // Create row components
        for (int row = 0; row < 6; row++) {
            List<Cell> cells = new ArrayList<>();
            for (int col = 0; col < 6; col++) {
                int index = row * 4 + col;
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
        for (int col = 0; col < 6; col++) {
            List<Cell> cells = new ArrayList<>();
            for (int row = 0; row < 6; row++) {
                int index = row * 4 + col;
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
        for (int box = 0; box < 6; box++) {
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

        return new BoardComponent(components, 6);
    }

    private int getBoxNumber(int col, int row) {
        return (row / 2) * 2 + (col / 2);
    }
}
