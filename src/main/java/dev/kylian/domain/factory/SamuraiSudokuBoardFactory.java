package dev.kylian.domain.factory;

import dev.kylian.domain.composite.*;
import dev.kylian.domain.strategy.ValueStrategy;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SamuraiSudokuBoardFactory implements SudokuBoardFactory {

    @Override
    public SudokuComponent createSudokuBoard(File file) {
        String[] lines = new String[5];
        lines[0] = "800000700003050206700300095000091840000007002000062000000000000609080000002903000";
        lines[1] = "149000000000091000000060000007120008000000340405008067000000000000007020000050003";
        lines[2] = "000000000000008000000004000010600005030070080800005010000900000000800000000000000";
        lines[3] = "900060000030400000000000000390800407065000000200037600000080000000190000000000914";
        lines[4] = "000402800000080902000000000000610000400800000098750000670008001901060700002000009";

        String[] offsets = new String[5];
        offsets[0] = "0,0";
        offsets[1] = "12,0";
        offsets[2] = "6,6";
        offsets[3] = "0,12";
        offsets[4] = "12,12";

        List<SudokuComponent> components = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            String line = lines[i];
            components.add(createBoardFromLine(line, Integer.parseInt(offsets[i].split(",")[0]), Integer.parseInt(offsets[i].split(",")[1])));
        }

         return new BoardComponent(components, 9);
    }

    private SudokuComponent createBoardFromLine(String line, int offsetX, int offsetY) {
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
                Point point = new Point(col+offsetX, row+offsetY);
                int box = getBoxNumber(col+offsetX, row+offsetY);
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
                Point point = new Point(col+offsetX, row+offsetY);
                int box = getBoxNumber(col+offsetX, row+offsetY);
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
                    Point point = new Point(col+offsetX, row+offsetY);
                    int boxNumber = getBoxNumber(col+offsetX, row+offsetY);
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
