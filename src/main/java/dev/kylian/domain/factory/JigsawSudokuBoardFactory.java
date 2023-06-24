package dev.kylian.domain.factory;

import dev.kylian.domain.SudokuFileReader;
import dev.kylian.domain.composite.SudokuComponent;

import java.io.File;

public class JigsawSudokuBoardFactory implements SudokuBoardFactory {
    private final SudokuFileReader reader;

    public JigsawSudokuBoardFactory(SudokuFileReader reader) {
        this.reader = reader;
    }

    @Override
    public SudokuComponent createSudokuBoard(File file) {
        return null;
    }
}
