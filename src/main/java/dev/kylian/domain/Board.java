package dev.kylian.domain;

import java.util.List;

public class Board implements SudokuComponent {
    private final List<SudokuComponent> components;
    private final int size;

    public Board(List<SudokuComponent> components, int size) {
        this.components = components;
        this.size = size;
    }

    @Override
    public boolean isValid() {
        boolean ret = true;
        for (SudokuComponent component : components) {
            if (!component.isValid()) {
                ret = false;
            }
        }
        return ret;
    }

    @Override
    public void setValue(int x, int y, int value) {
        if (value < 1 || value > size) {
            throw new IllegalArgumentException("Value must be between 1 and " + size);
        }
        components.forEach(component -> component.setValue(x, y, value));
    }

    @Override
    public String toString() {
        return "Board{" +
                "components=" + components +
                '}';
    }
}
