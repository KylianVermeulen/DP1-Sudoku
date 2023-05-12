package dev.kylian.domain.composite;

import dev.kylian.domain.strategy.NormalValueStrategy;
import dev.kylian.domain.strategy.ValueStrategy;
import dev.kylian.domain.visitor.Visitor;

import java.util.Set;

public class Cell implements SudokuComponent {
    private int value;
    private final Point point;
    private final int box;
    private final Set<Integer> helpValues;
    private final boolean isGiven;
    private final boolean isCorrect;
    private ValueStrategy valueStrategy;

    public Cell(int value, Point point, int box, Set<Integer> helpValues, boolean isGiven, boolean isCorrect, ValueStrategy valueStrategy) {
        this.value = value;
        this.point = point;
        this.box = box;
        this.helpValues = helpValues;
        this.isGiven = isGiven;
        this.isCorrect = isCorrect;
        this.valueStrategy = valueStrategy;
    }

    @Override
    public void setValue(int x, int y, int value) {
        if (isGiven) return;

        if (new Point(x, y).equals(point)) {
            if (valueStrategy == null) valueStrategy = new NormalValueStrategy();

            valueStrategy.setValue(this, value);
        }
    }

    @Override
    public boolean isValid() {
        return value != 0;
    }

    @Override
    public Cell copy() {
        return new Cell(this.value, this.point, this.box, this.helpValues, this.isGiven, this.isCorrect, null);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void setValueStrategy(ValueStrategy strategy) {
        this.valueStrategy = strategy;
    }

    public int getValue() {
        return value;
    }

    public void changeValue(int value) {
        this.value = (this.value != value) ? value : 0;
    }

    public void changeHelpValue(int value) {
        if (helpValues.contains(value)) {
            helpValues.remove(value);
        } else {
            helpValues.add(value);
        }
    }
}
