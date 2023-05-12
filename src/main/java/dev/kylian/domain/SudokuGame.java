package dev.kylian.domain;

import dev.kylian.domain.composite.Cell;
import dev.kylian.domain.composite.SudokuComponent;
import dev.kylian.domain.factory.BasicSudokuBoardFactory;
import dev.kylian.domain.factory.SudokuBoardFactory;
import dev.kylian.domain.strategy.NormalValueStrategy;
import dev.kylian.domain.strategy.ValueStrategy;
import dev.kylian.domain.visitor.CreateCellGridVisitor;

public class SudokuGame {
    private SudokuComponent sudoku;

    void initializeGame() {
        SudokuBoardFactory factory = new BasicSudokuBoardFactory();
        sudoku = factory.createSudokuBoard(null);

        ValueStrategy strategy = new NormalValueStrategy();
        sudoku.setValueStrategy(strategy);

        CreateCellGridVisitor visitor = new CreateCellGridVisitor();
        sudoku.accept(visitor);

        Cell[][] grid = visitor.getGrid();
    }
}
