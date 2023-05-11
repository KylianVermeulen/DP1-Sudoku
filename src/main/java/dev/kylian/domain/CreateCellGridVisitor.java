package dev.kylian.domain;

import java.util.HashMap;

public class CreateCellGridVisitor implements Visitor {

    public HashMap<Point, Cell> getCellGrid() {
        return new HashMap<>();
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
