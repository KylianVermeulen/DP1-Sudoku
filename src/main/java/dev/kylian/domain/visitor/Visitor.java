package dev.kylian.domain.visitor;

import dev.kylian.domain.composite.BoardComponent;
import dev.kylian.domain.composite.Cell;
import dev.kylian.domain.composite.CellGroupComponent;

public interface Visitor {
    void visit(Cell component);

    void visit(CellGroupComponent component);

    void visit(BoardComponent component);
}
