package dev.kylian.domain.composite;

import dev.kylian.domain.strategy.NormalValueStrategy;
import dev.kylian.domain.strategy.ValueStrategy;
import dev.kylian.domain.visitor.Visitor;

import java.util.Objects;
import java.util.Set;

public class Cell implements SudokuComponent {
    private int value;
    private Point point;
    private int box;
    private Set<Integer> helpValues;
    private boolean isGiven;
    private boolean isCorrect;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return value == cell.value && box == cell.box && isGiven == cell.isGiven && isCorrect == cell.isCorrect && Objects.equals(point, cell.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, point, box, isGiven, isCorrect);
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

    public int getValue() {
        return value;
    }

    public Point getPoint() {
        return point;
    }

    public boolean isGiven() {
        return isGiven;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public int getBox() {
        return box;
    }

    public Set<Integer> getHelpValues() {
        return helpValues;
    }

    public ValueStrategy getValueStrategy() {
        return valueStrategy;
    }
}
