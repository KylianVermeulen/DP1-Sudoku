package dev.kylian.domain;

import dev.kylian.domain.composite.SudokuComponent;
import dev.kylian.domain.factory.SudokuBoardFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class SudokuGameTest {
    private SudokuGame game;
    private SudokuBoardFactory factory;

    @BeforeEach
    public void setUp() {
        // Create mock factories
        factory = Mockito.mock(SudokuBoardFactory.class);
        Map<String, SudokuBoardFactory> factories = Map.of("test", factory);

        game = new SudokuGame(factories);
    }

    @Test
    public void testInitializeNewGame() {
        SudokuComponent component = Mockito.mock(SudokuComponent.class);
        when(factory.createSudokuBoard(Mockito.any(File.class))).thenReturn(component);

        game.initializeNewGame("test");

        assertSame(component, game.getSudoku());
    }
}