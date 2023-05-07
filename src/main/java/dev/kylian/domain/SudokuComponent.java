package dev.kylian.domain;

public interface SudokuComponent {
    boolean isValid();
    void setValue(int x, int y, int value);
}
