package dev.kylian.domain;

public interface Component {
    void place(Coordinate coordinate, int value, boolean locked);
    String draw();
}
