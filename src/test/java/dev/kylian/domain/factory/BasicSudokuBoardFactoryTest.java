package dev.kylian.domain.factory;

import dev.kylian.domain.SudokuFileReader;
import dev.kylian.domain.composite.BoardComponent;
import dev.kylian.domain.composite.Cell;
import dev.kylian.domain.composite.SudokuComponent;
import dev.kylian.domain.visitor.CreateCellGridVisitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class BasicSudokuBoardFactoryTest {
    private BasicSudokuBoardFactory factory;
    private SudokuFileReader fileReader;
    private CreateCellGridVisitor cellGridVisitor;

    @BeforeEach
    public void setUp() {
        fileReader = Mockito.mock(SudokuFileReader.class);
        cellGridVisitor = new CreateCellGridVisitor();
        factory = new BasicSudokuBoardFactory(fileReader);
    }

    @Test
    public void testCreateSudokuBoardValid() throws IOException {
        String validSudokuString = "123456789123456789123456789123456789123456789123456789123456789123456789123456789";
        when(fileReader.readFileToString(Mockito.any())).thenReturn(validSudokuString);

        SudokuComponent sudokuComponent = factory.createSudokuBoard(Mockito.mock(File.class));
        sudokuComponent.accept(cellGridVisitor);

        assertTrue(sudokuComponent instanceof BoardComponent);

        Cell[][] cellsGrid = cellGridVisitor.getGrid();
        int gridSize = cellGridVisitor.getBoxSize();

        assertEquals(9, gridSize);
        assertEquals(1, cellsGrid[0][0].getValue());
    }

    @Test
    public void testCreateSudokuBoardInvalid() throws IOException {
        String invalidSudokuString = "1234567891234567891234567891234567891234567891234567891234567891234567891234567891";
        when(fileReader.readFileToString(Mockito.any())).thenReturn(invalidSudokuString);

        assertThrows(IllegalArgumentException.class, () -> factory.createSudokuBoard(Mockito.mock(File.class)));
    }

    @Test
    public void testCreateSudokuBoardIOException() throws IOException {
        when(fileReader.readFileToString(Mockito.any())).thenThrow(new IOException());

        assertThrows(RuntimeException.class, () -> factory.createSudokuBoard(Mockito.mock(File.class)));
    }
}