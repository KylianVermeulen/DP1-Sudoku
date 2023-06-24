package dev.kylian.domain.factory;

import dev.kylian.domain.SudokuFileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class BoardFactoryTest {
    private BoardFactory boardFactory;

    @BeforeEach
    public void setup() {
        boardFactory = new BoardFactory();
    }

    @Test
    public void testDefaultFactories() {
        assertNotNull(boardFactory.getFactory("9x9"));
        assertSame(boardFactory.getFactory("9x9").getClass(), BasicSudokuBoardFactory.class);
        assertNotNull(boardFactory.getFactory("6x6"));
        assertSame(boardFactory.getFactory("6x6").getClass(), SixBySixSudokuBoardFactory.class);
        assertNotNull(boardFactory.getFactory("4x4"));
        assertSame(boardFactory.getFactory("4x4").getClass(), FourByFourSudokuBoardFactory.class);
        assertNotNull(boardFactory.getFactory("jigsaw"));
        assertSame(boardFactory.getFactory("jigsaw").getClass(), JigsawSudokuBoardFactory.class);
        assertNotNull(boardFactory.getFactory("samurai"));
        assertSame(boardFactory.getFactory("samurai").getClass(), SamuraiSudokuBoardFactory.class);
    }

    @Test
    public void testRegisterFactory() {
        SudokuFileReader reader = new SudokuFileReader();
        SudokuBoardFactory mockFactory = new BasicSudokuBoardFactory(reader);
        boardFactory.registerFactory("mock", mockFactory);
        assertEquals(mockFactory, boardFactory.getFactory("mock"));
    }

    @Test
    public void testCustomFactoryMap() {
        Map<String, SudokuBoardFactory> customFactories = new HashMap<>();
        SudokuFileReader reader = new SudokuFileReader();
        SudokuBoardFactory mockFactory = new BasicSudokuBoardFactory(reader);
        customFactories.put("mock", mockFactory);

        boardFactory = new BoardFactory(customFactories);
        assertEquals(mockFactory, boardFactory.getFactory("mock"));
    }
}