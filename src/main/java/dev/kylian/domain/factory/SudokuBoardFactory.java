package dev.kylian.domain.factory;

import dev.kylian.domain.composite.SudokuComponent;

import java.io.File;

public interface SudokuBoardFactory {
    SudokuComponent createSudokuBoard(File file);
}
