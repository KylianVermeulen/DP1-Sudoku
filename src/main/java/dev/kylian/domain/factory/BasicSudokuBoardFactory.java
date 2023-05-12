package dev.kylian.domain.factory;

import dev.kylian.domain.composite.SudokuComponent;

import java.io.File;

public class BasicSudokuBoardFactory implements SudokuBoardFactory {

    @Override
    public SudokuComponent createSudokuBoard(File file) {
        return null;
    }
}