package dev.kylian.domain.visitor;

import dev.kylian.domain.composite.BoardComponent;
import dev.kylian.domain.composite.Cell;
import dev.kylian.domain.composite.CellGroupComponent;

public class CreateCellGridVisitor implements Visitor {
    private int maxX;
    private int maxY;

    public Cell[][] getGrid() {
        return new Cell[maxX][maxY];
    }

    @Override
    public void visit(Cell component) {

    }

    @Override
    public void visit(CellGroupComponent component) {

    }

    @Override
    public void visit(BoardComponent component) {

    }
}
