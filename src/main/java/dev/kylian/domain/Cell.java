package dev.kylian.domain;

public class Cell implements SudokuComponent {
    private final int x;
    private final int y;
    private int value;
    private final boolean isLocked;
    private boolean wrong;

    public Cell(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
        isLocked = value != 0;
        wrong = false;
    }

    @Override
    public boolean isValid() {
        return value != 0;
    }

    @Override
    public void setValue(int x, int y, int value) {
        if (isLocked) {
            return;
        }
        if (x == this.x && y == this.y) {
            this.value = value;
        }
    }

    public int getValue() {
        return value;
    }

    public void setWrong(boolean wrong) {
        if (isLocked) {
            return;
        }
        this.wrong = wrong;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "x=" + x +
                ", y=" + y +
                ", value=" + value +
                ", isLocked=" + isLocked +
                ", wrong=" + wrong +
                '}';
    }
}
