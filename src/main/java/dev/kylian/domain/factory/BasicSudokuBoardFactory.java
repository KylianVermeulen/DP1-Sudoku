package dev.kylian.domain.factory;

import dev.kylian.domain.SudokuFileReader;
import dev.kylian.domain.composite.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BasicSudokuBoardFactory implements SudokuBoardFactory {
    private static final int SUDOKU_SIZE = 9;
    private static final int EXPECTED_INPUT_LENGTH = SUDOKU_SIZE * SUDOKU_SIZE;
    private static final String INVALID_LENGTH_MESSAGE = "Invalid line length. Expected length: " + EXPECTED_INPUT_LENGTH;

    private final SudokuFileReader reader;

    public BasicSudokuBoardFactory(SudokuFileReader reader) {
        this.reader = reader;
    }

    @Override
    public SudokuComponent createSudokuBoard(File file) {
        String line = readSudokuFile(file);

        System.out.println("line: " + line + " line.length(): " + line.length());
        if (line.length() != EXPECTED_INPUT_LENGTH) {
            throw new IllegalArgumentException(INVALID_LENGTH_MESSAGE);
        }

        List<SudokuComponent> components = new ArrayList<>();

        createRowComponents(line, components);
        createColumnComponents(line, components);
        createBoxComponents(line, components);

        return new BoardComponent(components, SUDOKU_SIZE);
    }

    private String readSudokuFile(File file) {
        try {
            return reader.readFileToString(file);
        } catch (Exception e) {
            throw new RuntimeException("Error reading file", e);
        }
    }

     private void createRowComponents(String line, List<SudokuComponent> components) {
        for (int row = 0; row < SUDOKU_SIZE; row++) {
            List<Cell> cells = new ArrayList<>();
            for (int col = 0; col < SUDOKU_SIZE; col++) {
                cells.add(createCell(line, col, row));
            }
            components.add(new CellGroupComponent(cells));
        }
    }

    private void createColumnComponents(String line, List<SudokuComponent> components) {
        for (int col = 0; col < SUDOKU_SIZE; col++) {
            List<Cell> cells = new ArrayList<>();
            for (int row = 0; row < SUDOKU_SIZE; row++) {
                cells.add(createCell(line, col, row));
            }
            components.add(new CellGroupComponent(cells));
        }
    }

    private void createBoxComponents(String line, List<SudokuComponent> components) {
        for (int box = 0; box < SUDOKU_SIZE; box++) {
            List<Cell> cells = new ArrayList<>();
            int startRow = box / 3 * 3;
            int startCol = box % 3 * 3;
            for (int row = startRow; row < startRow + 3; row++) {
                for (int col = startCol; col < startCol + 3; col++) {
                    cells.add(createCell(line, col, row));
                }
            }
            components.add(new CellGroupComponent(cells));
        }
    }

    private Cell createCell(String line, int col, int row) {
        int index = row * SUDOKU_SIZE + col;
        int value = Character.getNumericValue(line.charAt(index));
        boolean isGiven = (value != 0);
        Point point = new Point(col, row);
        int box = getBoxNumber(col, row);
        Set<Integer> helpValues = new HashSet<>();
        boolean isCorrect = true;
        return new Cell(value, point, box, helpValues, isGiven, isCorrect, null);
    }

    private int getBoxNumber(int col, int row) {
        return (row / 3) * 3 + (col / 3);
    }
}
