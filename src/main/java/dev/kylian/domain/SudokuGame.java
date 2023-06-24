package dev.kylian.domain;

import dev.kylian.domain.composite.SudokuComponent;
import dev.kylian.domain.factory.BoardFactory;
import dev.kylian.domain.factory.SudokuBoardFactory;
import dev.kylian.domain.strategy.NormalValueStrategy;
import dev.kylian.domain.strategy.ValueStrategy;

import java.io.File;
import java.util.Random;

public class SudokuGame {
    private final BoardFactory boardFactory;
    private SudokuComponent sudoku;

    public SudokuGame(BoardFactory boardFactory) {
        this.boardFactory = boardFactory;
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
        return boardFactory.getFactory(type);
    }
}
