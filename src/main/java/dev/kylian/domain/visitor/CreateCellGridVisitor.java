package dev.kylian.domain.visitor;

import dev.kylian.domain.composite.BoardComponent;
import dev.kylian.domain.composite.Cell;
import dev.kylian.domain.composite.CellGroupComponent;

import java.util.HashSet;

public class CreateCellGridVisitor implements Visitor {
    private int maxX;
    private int maxY;
    private final HashSet<Cell> cells;
    private int boxSize;

    public CreateCellGridVisitor() {
        maxX = 0;
        maxY = 0;
        cells = new HashSet<>();
    }

    public Cell[][] getGrid() {
        Cell[][] grid = new Cell[maxY+1][maxX+1];
        for (Cell cell : cells) {
            grid[cell.getPoint().y()][cell.getPoint().x()] = cell;
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
        boxSize = component.getSize();
    }

    public int getBoxSize() {
        return boxSize;
    }
}
