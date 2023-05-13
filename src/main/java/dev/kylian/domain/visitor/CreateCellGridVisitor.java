package dev.kylian.domain.visitor;

import dev.kylian.domain.composite.BoardComponent;
import dev.kylian.domain.composite.Cell;
import dev.kylian.domain.composite.CellGroupComponent;

import java.util.HashSet;

public class CreateCellGridVisitor implements Visitor {
    private int maxX;
    private int maxY;
    private final HashSet<Cell> cells;

    public CreateCellGridVisitor() {
        maxX = 0;
        maxY = 0;
        cells = new HashSet<>();
    }

    public Cell[][] getGrid() {
        Cell[][] grid = new Cell[maxX][maxY];
        for (Cell cell : cells) {
            grid[cell.getPoint().x()][cell.getPoint().y()] = cell;
        }
        return grid;
    }

    @Override
    public void visit(Cell component) {
        if (component.getPoint().x() > maxX) maxX = component.getPoint().x();
        if (component.getPoint().y() > maxY) maxY = component.getPoint().y();
        cells.add(component);
    }

    @Override
    public void visit(CellGroupComponent component) {

    }

    @Override
    public void visit(BoardComponent component) {

    }
}
