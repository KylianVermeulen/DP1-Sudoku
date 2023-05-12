package dev.kylian.domain.strategy;

import dev.kylian.domain.composite.Cell;

public class NormalValueStrategy implements ValueStrategy {

    @Override
    public void setValue(Cell cell, int value) {
        cell.changeValue(value);
    }
}
