package dev.kylian.domain.strategy;

import dev.kylian.domain.composite.Cell;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class HelpValueStrategyTest {
    @Test
    public void testSetValue() {
        Cell cell = Mockito.mock(Cell.class);
        HelpValueStrategy strategy = new HelpValueStrategy();

        strategy.setValue(cell, 5);

        Mockito.verify(cell).changeHelpValue(5);
    }
}