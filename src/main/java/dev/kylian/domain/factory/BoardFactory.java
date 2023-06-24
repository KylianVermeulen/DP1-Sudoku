package dev.kylian.domain.factory;

import dev.kylian.domain.SudokuFileReader;

import java.util.HashMap;
import java.util.Map;

public class BoardFactory {
    private final Map<String, SudokuBoardFactory> factories;

    public BoardFactory() {
        SudokuFileReader reader = new SudokuFileReader();
        this.factories = new HashMap<>(Map.of(
                "9x9", new BasicSudokuBoardFactory(reader),
                "6x6", new SixBySixSudokuBoardFactory(reader),
                "4x4", new FourByFourSudokuBoardFactory(reader),
                "jigsaw", new JigsawSudokuBoardFactory(reader),
                "samurai", new SamuraiSudokuBoardFactory(reader)
        ));
    }

    public BoardFactory(Map<String, SudokuBoardFactory> factories) {
        this.factories = factories;
    }

    public void registerFactory(String type, SudokuBoardFactory factory) {
        factories.put(type, factory);
    }

    public SudokuBoardFactory getFactory(String type) {
        return factories.get(type);
    }
}
