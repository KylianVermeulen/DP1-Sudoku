package dev.kylian.domain.composite;

import dev.kylian.domain.strategy.ValueStrategy;
import dev.kylian.domain.visitor.Visitor;

import java.util.List;

public class CellGroupComponent implements SudokuComponent {
    private final List<Cell> cells;

    public CellGroupComponent(List<Cell> cells) {
        this.cells = cells;
    }

    @Override
    public void setValue(int x, int y, int value) {
        cells.forEach(cell -> cell.setValue(x, y, value));
    }

    @Override
    public boolean isValid() {
        for (int i = 0; i < cells.size(); i++) {
            for (int j = i + 1; j < cells.size(); j++) {
                if (cells.get(i).getValue() == cells.get(j).getValue() || !cells.get(i).isValid() || !cells.get(j).isValid()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public SudokuComponent copy() {
        return new CellGroupComponent(cells.stream().map(Cell::copy).toList());
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
        cells.forEach(cell -> cell.accept(visitor));
    }

    @Override
    public void setValueStrategy(ValueStrategy strategy) {
        cells.forEach(cell -> cell.setValueStrategy(strategy));
    }
}
