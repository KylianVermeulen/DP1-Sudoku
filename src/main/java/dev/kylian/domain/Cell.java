package dev.kylian.domain;

public class Cell implements Component {
    private Coordinate coordinate;
    private Integer value;
    private Boolean locked;

    public Cell(Coordinate coordinate) {
        this.coordinate = coordinate;
        this.locked = false;
    }

    @Override
    public void place(Coordinate coordinate, int value, boolean locked) {
        if (coordinate.equals(this.coordinate)) {
            if (!this.locked || locked) {
                this.value = value;
                this.locked = locked;
            }
        }
    }

    @Override
    public String draw() {
        return this.toString();
    }

    @Override
    public String toString() {
        return "Cell{" +
                "coordinate=" + coordinate +
                ", value=" + value +
                ", locked=" + locked +
                '}';
    }
}
