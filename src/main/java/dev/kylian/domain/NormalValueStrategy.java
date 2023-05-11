package dev.kylian.domain;

public class NormalValueStrategy implements ValueStrategy {

    @Override
    public void setValue(Cell cell, int value) {
        cell.changeValue(value);
    }
}
