package dev.kylian.controller;

import dev.kylian.ui.FileSelectionView;

import java.util.Set;

public class MenuController {

    public MenuController() {
        Set<String> fileNames = Set.of("4x4", "6x6", "9x9", "jigsaw", "samurai");
        FileSelectionView fileSelectionView = new FileSelectionView(this, fileNames);
        fileSelectionView.render();
    }

    public void actionFileSelected(String type) {
        System.out.println("Type selected: " + type);
        new GameController(type);
    }
}
