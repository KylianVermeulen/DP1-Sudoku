package dev.kylian.domain;

import java.util.List;

public class BoardComponent implements SudokuComponent {
    private final List<SudokuComponent> components;
    private final int size;

    public BoardComponent(List<SudokuComponent> components, int size) {
        this.components = components;
        this.size = size;
    }

    @Override
    public void setValue(int x, int y, int value) {
        if (value < 1 || value > size) {
            throw new IllegalArgumentException("Value must be between 1 and " + size);
        }
        components.forEach(component -> component.setValue(x, y, value));
    }

    @Override
    public boolean isValid() {
        return components.stream().allMatch(SudokuComponent::isValid);
    }

    @Override
    public SudokuComponent copy() {
        return new BoardComponent(components.stream().map(SudokuComponent::copy).toList(), size);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
        components.forEach(component -> component.accept(visitor));
    }

    @Override
    public void setValueStrategy(ValueStrategy strategy) {
        components.forEach(component -> component.setValueStrategy(strategy));
    }
}
