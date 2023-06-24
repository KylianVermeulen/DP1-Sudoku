package dev.kylian.domain.visitor;

import dev.kylian.domain.composite.BoardComponent;
import dev.kylian.domain.composite.Cell;
import dev.kylian.domain.composite.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateCellGridVisitorTest {
    private CreateCellGridVisitor visitor;

    @BeforeEach
    public void setUp() {
        visitor = new CreateCellGridVisitor();
    }

    @Test
    public void testVisitCell() {
        Cell cell = new Cell(1, new Point(2, 3), 0, null, true, true, null);
        visitor.visit(cell);
        Cell[][] grid = visitor.getGrid();
        assertEquals(1, grid[3][2].getValue());
    }

    @Test
    public void testVisitBoardComponent() {
        BoardComponent board = new BoardComponent(null, 4);
        visitor.visit(board);
        assertEquals(4, visitor.getBoxSize());
    }

    @Test
    public void testVisitCellGroupComponent() {
        // Since the visit method for CellGroupComponent is empty, there's nothing to test
    }
}