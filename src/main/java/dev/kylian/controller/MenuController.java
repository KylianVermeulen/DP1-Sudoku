package dev.kylian.controller;

import dev.kylian.ui.FileSelectionView;

import java.io.PrintWriter;
import java.util.Set;

public class MenuController {
    private final PrintWriter printWriter;

    public MenuController(PrintWriter printWriter) {
        this.printWriter = printWriter;
        Set<String> fileNames = Set.of("4x4", "6x6", "9x9", "jigsaw", "samurai");
        FileSelectionView fileSelectionView = new FileSelectionView(this, fileNames, printWriter);
        fileSelectionView.render();
    }

    public void actionFileSelected(String type) {
        System.out.println("Type selected: " + type);
        new GameController(type, printWriter);
    }
}
