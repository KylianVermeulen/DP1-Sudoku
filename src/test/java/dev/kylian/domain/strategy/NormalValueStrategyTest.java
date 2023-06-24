package dev.kylian.domain.strategy;

import dev.kylian.domain.composite.Cell;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class NormalValueStrategyTest {
    @Test
    public void testSetValue() {
        Cell cell = Mockito.mock(Cell.class);
        NormalValueStrategy strategy = new NormalValueStrategy();

        strategy.setValue(cell, 5);

        Mockito.verify(cell).changeValue(5);
    }
}