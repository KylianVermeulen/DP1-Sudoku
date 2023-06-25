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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class JigsawSudokuBoardFactoryTest {
    private JigsawSudokuBoardFactory factory;
    private SudokuFileReader fileReader;
    private CreateCellGridVisitor cellGridVisitor;

    @BeforeEach
    public void setUp() {
        fileReader = Mockito.mock(SudokuFileReader.class);
        cellGridVisitor = new CreateCellGridVisitor();
        factory = new JigsawSudokuBoardFactory(fileReader);
    }

    @Test
    public void testCreateSudokuBoardValid() throws IOException {
        String validSudokuString = "SumoCueV1=0J0=8J0=0J0=0J1=0J2=0J2=0J2=5J2=0J2=8J3=0J0=0J1=0J1=0J2=7J2=0J4=0J4=5J4=0J3=0J0=0J1=0J1=9J2=0J2=0J5=0J6=0J4=0J3=7J0=0J1=1J1=6J5=9J5=0J5=0J6=0J4=0J3=0J0=4J1=3J1=0J5=1J7=8J7=0J6=0J4=0J3=0J0=0J5=8J5=7J5=6J7=0J7=3J6=0J4=0J3=0J0=0J5=0J8=5J8=0J7=0J7=0J6=0J4=3J3=0J3=0J3=6J8=0J8=0J7=0J7=0J6=2J4=0J8=9J8=0J8=0J8=0J8=0J7=0J6=8J6=0J6";
        when(fileReader.readFileToString(Mockito.any())).thenReturn(validSudokuString);

        SudokuComponent sudokuComponent = factory.createSudokuBoard(Mockito.mock(File.class));
        sudokuComponent.accept(cellGridVisitor);

        assertTrue(sudokuComponent instanceof BoardComponent);

        Cell[][] cellsGrid = cellGridVisitor.getGrid();
        int gridSize = cellGridVisitor.getBoxSize();

        assertEquals(9, gridSize);
        assertEquals(8, cellsGrid[0][1].getValue());
        assertEquals(2, cellsGrid[0][4].getBox());
    }

    @Test
    public void testCreateSudokuBoardInvalid() throws IOException {
        String invalidSudokuString = "SumoCueV1=0J0=8J0=0J0=0J1=0J2=0J2=0J2=5J2=0J2=8J3=0J0=0J1=0J1=0J2=7J2=0J4=0J4=5J4=0J3=0J0=0J1=0J1=9J2=0J2=0J5=0J6=0J4=0J3=7J0=0J1=1J1=6J5=9J5=0J5=0J6=0J4=0J3=0J0=4J1=3J1=0J5=1J7=8J7=0J6=0J4=0J3=0J0=0J5=8J5=7J5=6J7=0J7=3J6=0J4=0J3=0J0=0J5=0J8=5J8=0J7=0J7=0J6=0J4=3J3=0J3=0J3=6J8=0J8=0J7=0J7=0J6=2J4=0J8=9J8=0J8=0J8=0J8=0J7=0J6=8J6=0J6=";
        when(fileReader.readFileToString(Mockito.any())).thenReturn(invalidSudokuString);

        assertThrows(IllegalArgumentException.class, () -> factory.createSudokuBoard(Mockito.mock(File.class)));
    }

    @Test
    public void testCreateSudokuBoardIOException() throws IOException {
        when(fileReader.readFileToString(Mockito.any())).thenThrow(new IOException());

        assertThrows(RuntimeException.class, () -> factory.createSudokuBoard(Mockito.mock(File.class)));
    }
}