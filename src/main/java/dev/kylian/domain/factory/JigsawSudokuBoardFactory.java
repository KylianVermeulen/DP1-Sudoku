package dev.kylian.domain.factory;

import dev.kylian.domain.SudokuFileReader;
import dev.kylian.domain.composite.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JigsawSudokuBoardFactory implements SudokuBoardFactory {
    private static final int SUDOKU_SIZE = 9;
    private static final int EXPECTED_INPUT_LENGTH = SUDOKU_SIZE * SUDOKU_SIZE * 4;
    private static final String INVALID_LENGTH_MESSAGE = "Invalid line length. Expected length: " + EXPECTED_INPUT_LENGTH;

    private final SudokuFileReader reader;

    public JigsawSudokuBoardFactory(SudokuFileReader reader) {
        this.reader = reader;
    }

    @Override
    public SudokuComponent createSudokuBoard(File file) {
        String line = readSudokuFile(file).substring(9);

        System.out.println("line: " + line + " line.length(): " + line.length());
        if (line.length() != EXPECTED_INPUT_LENGTH) {
            throw new IllegalArgumentException(INVALID_LENGTH_MESSAGE);
        }

        List<String> values = new ArrayList<>();
        for (int i = 0; i < line.length(); i += 4) {
            values.add(line.substring(i, i + 4));
        }

        List<SudokuComponent> components = new ArrayList<>();

        createRowComponents(values, components);
        createColumnComponents(values, components);
        createBoxComponents(values, components);

        return new BoardComponent(components, SUDOKU_SIZE);
    }

    private String readSudokuFile(File file) {
        try {
            return reader.readFileToString(file);
        } catch (Exception e) {
            throw new RuntimeException("Error reading file", e);
        }
    }

    private void createRowComponents(List<String> values, List<SudokuComponent> components) {
        for (int row = 0; row < SUDOKU_SIZE; row++) {
            List<Cell> cells = new ArrayList<>();
            for (int col = 0; col < SUDOKU_SIZE; col++) {
                cells.add(createCell(values, col, row));
            }
            components.add(new CellGroupComponent(cells));
        }
    }

    private void createColumnComponents(List<String> values, List<SudokuComponent> components) {
        for (int col = 0; col < SUDOKU_SIZE; col++) {
            List<Cell> cells = new ArrayList<>();
            for (int row = 0; row < SUDOKU_SIZE; row++) {
                cells.add(createCell(values, col, row));
            }
            components.add(new CellGroupComponent(cells));
        }
    }

    private void createBoxComponents(List<String> values, List<SudokuComponent> components) {
        for (int box = 0; box < SUDOKU_SIZE; box++) {
            List<Cell> cells = new ArrayList<>();
            for (int row = 0; row < SUDOKU_SIZE; row++) {
                for (int col = 0; col < SUDOKU_SIZE; col++) {
                    if (createCell(values, col, row).getBox() == box) {
                        cells.add(createCell(values, col, row));
                    }
                }
            }
            components.add(new CellGroupComponent(cells));
        }
    }

    private Cell createCell(List<String> values, int col, int row) {
        int index = row * SUDOKU_SIZE + col;
        int value = Character.getNumericValue(values.get(index).charAt(1));
        boolean isGiven = (value != 0);
        Point point = new Point(col, row);
        int box = Character.getNumericValue(values.get(index).charAt(3));
        Set<Integer> helpValues = new HashSet<>();
        boolean isCorrect = true;
        return new Cell(value, point, box, helpValues, isGiven, isCorrect, null);
    }
}
