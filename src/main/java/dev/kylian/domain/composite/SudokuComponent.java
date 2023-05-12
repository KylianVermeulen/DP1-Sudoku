package dev.kylian.domain.composite;

import dev.kylian.domain.strategy.ValueStrategy;
import dev.kylian.domain.visitor.Visitor;

public interface SudokuComponent {
    void setValue(int x, int y, int value);
    boolean isValid();
    SudokuComponent copy();
    void accept(Visitor visitor);
    void setValueStrategy(ValueStrategy strategy);
}
