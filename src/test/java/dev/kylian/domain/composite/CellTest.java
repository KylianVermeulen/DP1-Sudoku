package dev.kylian.domain.composite;

import dev.kylian.domain.strategy.ValueStrategy;
import dev.kylian.domain.visitor.Visitor;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CellTest {
    @Test
    public void testSetValueWhenIsGiven() {
        Set<Integer> helpValues = new HashSet<>();
        ValueStrategy strategy = (cell, value) -> cell.changeValue(value);
        Cell cell = new Cell(1, new Point(1,1), 1, helpValues, true, true, strategy);

        cell.setValue(1, 1, 2);

        assertEquals(1, cell.getValue());
    }

    @Test
    public void testSetValueWhenNotGiven() {
        Set<Integer> helpValues = new HashSet<>();
        ValueStrategy strategy = (cell, value) -> cell.changeValue(value);
        Cell cell = new Cell(1, new Point(1,1), 1, helpValues, false, true, strategy);

        cell.setValue(1, 1, 5);

        assertEquals(5, cell.getValue());
    }

    @Test
    public void testIsValid() {
        Set<Integer> helpValues = new HashSet<>();
        ValueStrategy strategy = (cell, value) -> {};
        Cell cell = new Cell(0, new Point(1,1), 1, helpValues, true, true, strategy);

        assertFalse(cell.isValid());
    }

    @Test
    public void testEquals() {
        Set<Integer> helpValues1 = new HashSet<>();
        Set<Integer> helpValues2 = new HashSet<>();
        ValueStrategy strategy1 = (cell, value) -> {};
        ValueStrategy strategy2 = (cell, value) -> {};
        Cell cell1 = new Cell(1, new Point(1,1), 1, helpValues1, true, true, strategy1);
        Cell cell2 = new Cell(1, new Point(1,1), 1, helpValues2, true, true, strategy2);

        assertEquals(cell1, cell2);
    }

    @Test
    public void testChangeValue() {
        Set<Integer> helpValues = new HashSet<>();
        ValueStrategy strategy = (cell, value) -> {};
        Cell cell = new Cell(1, new Point(1,1), 1, helpValues, true, true, strategy);

        cell.changeValue(2);

        assertEquals(2, cell.getValue());
    }

    @Test
    public void testChangeHelpValue() {
        Set<Integer> helpValues = new HashSet<>();
        ValueStrategy strategy = (cell, value) -> {};
        Cell cell = new Cell(1, new Point(1,1), 1, helpValues, true, true, strategy);

        cell.changeHelpValue(2);

        assertTrue(cell.getHelpValues().contains(2));
    }

    @Test
    public void testAccept() {
        Visitor visitor = new Visitor() {
            @Override
            public void visit(Cell cell) {
                cell.changeValue(3);
            }

            @Override
            public void visit(CellGroupComponent component) {
            }

            @Override
            public void visit(BoardComponent component) {
            }
        };
        Set<Integer> helpValues = new HashSet<>();
        ValueStrategy strategy = (cell, value) -> {};
        Cell cell = new Cell(1, new Point(1,1), 1, helpValues, true, true, strategy);

        cell.accept(visitor);

        assertEquals(3, cell.getValue());
    }

    @Test
    public void testCopy() {
        Set<Integer> helpValues = new HashSet<>();
        helpValues.add(2);
        ValueStrategy strategy = (cell, value) -> {};
        Cell originalCell = new Cell(1, new Point(1,1), 1, helpValues, true, true, strategy);

        Cell copiedCell = originalCell.copy();

        assertEquals(originalCell, copiedCell);
        assertNotSame(originalCell, copiedCell);
    }

    @Test
    public void testHashCode() {
        Set<Integer> helpValues1 = new HashSet<>();
        Set<Integer> helpValues2 = new HashSet<>();
        ValueStrategy strategy1 = (cell, value) -> {};
        ValueStrategy strategy2 = (cell, value) -> {};
        Cell cell1 = new Cell(1, new Point(1,1), 1, helpValues1, true, true, strategy1);
        Cell cell2 = new Cell(1, new Point(1,1), 1, helpValues2, true, true, strategy2);

        assertEquals(cell1.hashCode(), cell2.hashCode());
    }

    @Test
    public void testSetValueStrategy() {
        Set<Integer> helpValues = new HashSet<>();
        ValueStrategy strategy = (cell, value) -> cell.changeValue(value);
        Cell cell = new Cell(1, new Point(1,1), 1, helpValues, false, true, null);

        cell.setValueStrategy(strategy);
        cell.setValue(1, 1, 5);

        assertEquals(5, cell.getValue());
    }

    @Test
    public void testChangeHelpValueRemoval() {
        Set<Integer> helpValues = new HashSet<>();
        helpValues.add(3);
        ValueStrategy strategy = (cell, value) -> {};
        Cell cell = new Cell(1, new Point(1,1), 1, helpValues, true, true, strategy);

        cell.changeHelpValue(3);

        assertFalse(cell.getHelpValues().contains(3));
    }

    @Test
    public void testIsGiven() {
        Cell cell = new Cell(1, new Point(1,1), 1, null, true, true, null);
        assertTrue(cell.isGiven());
    }

    @Test
    public void testisCorrect() {
        Cell cell = new Cell(1, new Point(1,1), 1, null, true, true, null);
        assertTrue(cell.isCorrect());
    }

    @Test
    public void testGetBox() {
        Cell cell = new Cell(1, new Point(1,1), 1, null, true, true, null);
        assertEquals(1, cell.getBox());
    }
}