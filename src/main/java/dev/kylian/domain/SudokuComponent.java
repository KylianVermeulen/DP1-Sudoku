package dev.kylian.domain;

public interface SudokuComponent {
    void setValue(int x, int y, int value);
    boolean isValid();
    SudokuComponent copy();
    void accept(Visitor visitor);
    void setValueStrategy(ValueStrategy strategy);
}
