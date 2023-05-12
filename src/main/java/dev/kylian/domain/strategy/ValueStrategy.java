package dev.kylian.domain.strategy;

import dev.kylian.domain.composite.Cell;

public interface ValueStrategy {
    void setValue(Cell cell, int value);
}
