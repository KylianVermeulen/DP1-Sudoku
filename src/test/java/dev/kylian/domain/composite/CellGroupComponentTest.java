package dev.kylian.domain.composite;

import dev.kylian.domain.strategy.NormalValueStrategy;
import dev.kylian.domain.strategy.ValueStrategy;
import dev.kylian.domain.visitor.Visitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CellGroupComponentTest {
    private CellGroupComponent cellGroupComponent;
    private List<Cell> cells;

    @BeforeEach
    public void setup() {
        cells = new ArrayList<>();
        cells.add(new Cell(1, new Point(0,0), 0, new HashSet<>(), false, true, null));
        cells.add(new Cell(2, new Point(1,0), 0, new HashSet<>(), false, true, null));
        cellGroupComponent = new CellGroupComponent(cells);
    }

    @Test
    public void testIsValid() {
        assertTrue(cellGroupComponent.isValid());

        cells.get(1).changeValue(1);
        assertFalse(cellGroupComponent.isValid());
    }

    @Test
    public void testCopy() {
        CellGroupComponent copy = (CellGroupComponent) cellGroupComponent.copy();
        assertNotSame(cellGroupComponent, copy);
        assertEquals(cellGroupComponent.isValid(), copy.isValid());
    }

    @Test
    public void testAccept() {
        ValueStrategy valueStrategy = (cell, value) -> cell.changeValue(value);
        cellGroupComponent.setValueStrategy(valueStrategy);

        cellGroupComponent.accept(new Visitor() {
            @Override
            public void visit(Cell component) {
                component.setValue(0, 0, 3);
            }

            @Override
            public void visit(CellGroupComponent component) {}

            @Override
            public void visit(BoardComponent component) {}
        });

        assertEquals(3, cells.get(0).getValue());
    }

    @Test
    public void testSetValue() {
        cellGroupComponent.setValue(0, 0, 5);
        assertEquals(5, cells.get(0).getValue());
    }

    @Test
    public void testSetValueStrategy() {
        ValueStrategy valueStrategy = (cell, value) -> cell.changeValue(value);
        cellGroupComponent.setValueStrategy(valueStrategy);
        assertEquals(valueStrategy, cells.get(0).getValueStrategy());
    }
}