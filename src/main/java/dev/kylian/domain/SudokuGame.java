package dev.kylian.domain;

import dev.kylian.domain.composite.SudokuComponent;
import dev.kylian.domain.factory.BasicSudokuBoardFactory;
import dev.kylian.domain.factory.JigsawSudokuBoardFactory;
import dev.kylian.domain.factory.SamuraiSudokuBoardFactory;
import dev.kylian.domain.factory.SudokuBoardFactory;
import dev.kylian.domain.strategy.NormalValueStrategy;
import dev.kylian.domain.strategy.ValueStrategy;

import java.util.Map;

public class SudokuGame {
    private SudokuComponent sudoku;
    private final Map<String, SudokuBoardFactory> factories;

    public SudokuGame() {
        factories = Map.of(
                "basic", new BasicSudokuBoardFactory(),
                "jigsaw", new JigsawSudokuBoardFactory(),
                "samurai", new SamuraiSudokuBoardFactory()
        );
    }

    public void initializeGame(String type) {
        SudokuBoardFactory factory = getFactory(type);
        sudoku = factory.createSudokuBoard(null);

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
