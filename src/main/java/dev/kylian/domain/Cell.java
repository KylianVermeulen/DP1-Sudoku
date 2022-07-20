package dev.kylian.domain;

public class Cell implements Component {
    private final Coordinate coordinate;
    private Integer value;

    public Cell(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public boolean validate() {
        return true;
    }
}
