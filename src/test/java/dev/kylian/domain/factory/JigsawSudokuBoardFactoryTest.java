package dev.kylian.domain.factory;

import dev.kylian.domain.SudokuFileReader;
import dev.kylian.domain.composite.SudokuComponent;
import dev.kylian.domain.visitor.CreateCellGridVisitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertNull;

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
    public void testCreateSudokuBoardValid() {
        SudokuComponent sudokuComponent = factory.createSudokuBoard(Mockito.mock(File.class));
        assertNull(sudokuComponent);
    }
}