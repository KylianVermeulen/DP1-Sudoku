package dev.kylian.domain.factory;

import dev.kylian.domain.SudokuFileReader;
import dev.kylian.domain.composite.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FourByFourSudokuBoardFactory implements SudokuBoardFactory {
    private final SudokuFileReader reader;

    public FourByFourSudokuBoardFactory(SudokuFileReader reader) {
        this.reader = reader;
    }

    @Override
    public SudokuComponent createSudokuBoard(File file) {
        String line = readSudokuFile(file);

        System.out.println("line: " + line + " line.length(): " + line.length());
        if (line.length() != 16) {
            throw new IllegalArgumentException("Invalid line length. Expected length: 81");
        }

        List<SudokuComponent> components = new ArrayList<>();

        createRowComponents(line, components);
        createColumnComponents(line, components);
        createBoxComponents(line, components);

        return new BoardComponent(components, 4);
    }

    private String readSudokuFile(File file) {
        try {
            return reader.readFileToString(file);
        } catch (Exception e) {
            throw new RuntimeException("Error reading file", e);
        }
    }

    private void createRowComponents(String line, List<SudokuComponent> components) {
        for (int row = 0; row < 4; row++) {
            List<Cell> cells = new ArrayList<>();
            for (int col = 0; col < 4; col++) {
                cells.add(createCell(line, col, row));
            }
            components.add(new CellGroupComponent(cells));
        }
    }

    private void createColumnComponents(String line, List<SudokuComponent> components) {
        for (int col = 0; col < 4; col++) {
            List<Cell> cells = new ArrayList<>();
            for (int row = 0; row < 4; row++) {
                cells.add(createCell(line, col, row));
            }
            components.add(new CellGroupComponent(cells));
        }
    }

    private void createBoxComponents(String line, List<SudokuComponent> components) {
        for (int box = 0; box < 4; box++) {
            List<Cell> cells = new ArrayList<>();
            int startRow = box / 2 * 2;
            int startCol = box % 2 * 2;
            for (int row = startRow; row < startRow + 2; row++) {
                for (int col = startCol; col < startCol + 2; col++) {
                    cells.add(createCell(line, col, row));
                }
            }
            components.add(new CellGroupComponent(cells));
        }
    }

    private Cell createCell(String line, int col, int row) {
        int index = row * 4 + col;
        int value = Character.getNumericValue(line.charAt(index));
        boolean isGiven = (value != 0);
        Point point = new Point(col, row);
        int boxNumber = getBoxNumber(col, row);
        Set<Integer> helpValues = new HashSet<>();
        boolean isCorrect = true;
        return new Cell(value, point, boxNumber, helpValues, isGiven, isCorrect, null);
    }

    private int getBoxNumber(int col, int row) {
        return (row / 2) * 2 + (col / 2);
    }
}
