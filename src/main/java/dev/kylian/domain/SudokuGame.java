package dev.kylian.domain;

import dev.kylian.domain.composite.SudokuComponent;
import dev.kylian.domain.factory.*;
import dev.kylian.domain.strategy.NormalValueStrategy;
import dev.kylian.domain.strategy.ValueStrategy;

import java.io.File;
import java.util.Map;
import java.util.Random;

public class SudokuGame {
    private SudokuComponent sudoku;
    private final Map<String, SudokuBoardFactory> factories;

    public SudokuGame() {
        factories = Map.of(
                "9x9", new BasicSudokuBoardFactory(),
                "6x6", new BasicSudokuBoardFactory(),
                "4x4", new FourByFourSudokuBoardFactory(),
                "jigsaw", new JigsawSudokuBoardFactory(),
                "samurai", new SamuraiSudokuBoardFactory()
        );
    }

    public void initializeNewGame(String type) {
        Random random = new Random();
        int i = random.nextInt(1, 4);
        File file = new File("src/main/resources/puzzle" + ((i != 1) ? i : "") + "." + type);

        SudokuBoardFactory factory = getFactory(type);
        sudoku = factory.createSudokuBoard(file);

        ValueStrategy strategy = new NormalValueStrategy();
        sudoku.setValueStrategy(strategy);
    }

    public SudokuComponent getSudoku() {
        return sudoku;
    }

    private SudokuBoardFactory getFactory(String type) {
        return factories.get(type);
    }
}
