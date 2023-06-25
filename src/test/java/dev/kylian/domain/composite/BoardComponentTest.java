package dev.kylian.domain.composite;

import dev.kylian.domain.strategy.ValueStrategy;
import dev.kylian.domain.visitor.Visitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BoardComponentTest {
    private BoardComponent boardComponent;
    private List<SudokuComponent> components;
    private List<Cell> cells;
    private int size;

    @BeforeEach
    public void setup() {
        components = new ArrayList<>();
        cells = new ArrayList<>();
        cells.add(new Cell(1, new Point(0, 0), 0, new HashSet<>(), false, true, null));
        cells.add(new Cell(2, new Point(1, 0), 0, new HashSet<>(), false, true, null));
        components.add(new CellGroupComponent(cells));
        size = 2;
        boardComponent = new BoardComponent(components, size);
    }

    @Test
    public void testIsValid() {
        assertTrue(boardComponent.isValid());

        cells.get(1).changeValue(1);

        assertFalse(boardComponent.isValid());
    }

    @Test
    public void testCopy() {
        BoardComponent copy = (BoardComponent) boardComponent.copy();
        assertNotSame(boardComponent, copy);
        assertEquals(boardComponent.isValid(), copy.isValid());
    }

    @Test
    public void testAccept() {
        ValueStrategy valueStrategy = (cell, value) -> cell.changeValue(value);
        boardComponent.setValueStrategy(valueStrategy);

        boardComponent.accept(new Visitor() {
            @Override
            public void visit(Cell component) {
                component.setValue(0, 0, 3);
            }

            @Override
            public void visit(CellGroupComponent component) {
            }

            @Override
            public void visit(BoardComponent component) {
            }
        });

        assertEquals(3, cells.get(0).getValue());
    }

    @Test
    public void testSetValue() {
        boardComponent.setValue(0, 0, 2);
        assertEquals(2, cells.get(0).getValue());
    }

    @Test
    public void testSetInvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> boardComponent.setValue(0, 0, 3));
    }

    @Test
    public void testSetValueStrategy() {
        ValueStrategy valueStrategy = (cell, value) -> cell.changeValue(value);
        boardComponent.setValueStrategy(valueStrategy);
        assertEquals(valueStrategy, cells.get(0).getValueStrategy());
    }

    @Test
    public void testGetSize() {
        assertEquals(size, boardComponent.getSize());
    }
}