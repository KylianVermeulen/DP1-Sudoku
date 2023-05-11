package dev.kylian.domain;

public interface Visitor {
    void visit(Cell component);
    void visit(CellGroupComponent component);
    void visit(BoardComponent component);
}
