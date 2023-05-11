package dev.kylian.domain;

public class HelpValueStrategy implements ValueStrategy {

    @Override
    public void setValue(Cell cell, int value) {
        cell.changeHelpValue(value);
    }
}
