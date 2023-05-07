package dev.kylian.domain;

import java.util.Arrays;

public class UniqueValueComponent implements SudokuComponent {
    private final Cell[] cells;

    public UniqueValueComponent(Cell[] cells) {
        this.cells = cells;
    }

    @Override
    public boolean isValid() {
        // check if all cells have a unique value and not 0
        boolean ret = true;
        for (int i = 0; i < cells.length; i++) {
            for (int j = i + 1; j < cells.length; j++) {
//                System.out.println("Comparing " + cells[i].toString() + " and " + cells[j].toString());
                if (cells[i].getValue() == 0 || cells[j].getValue() == 0) {
                    ret = false;
                }
                if (cells[i].getValue() == cells[j].getValue()) {
                    if (cells[i].getValue() != 0) {
                        System.out.println("Duplicate value found: " + cells[i].toString() + " and " + cells[j].toString());
                        cells[i].setWrong(true);
                        cells[j].setWrong(true);
                    }
                    ret = false;
                }
            }
        }
        return ret;
    }

    @Override
    public void setValue(int x, int y, int value) {
        for (SudokuComponent component : cells) {
            component.setValue(x, y, value);
        }
    }

    @Override
    public String toString() {
        return "UniqueValueComponent{" +
                "cells=" + Arrays.toString(cells) +
                '}';
    }
}
