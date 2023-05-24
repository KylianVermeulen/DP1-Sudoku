package dev.kylian.ui;

import dev.kylian.controller.MenuController;

import java.util.Scanner;
import java.util.Set;

public class FileSelectionView {
    private final MenuController menuController;
    private final Set<String> types;

    public FileSelectionView(MenuController menuController, Set<String> types) {
        this.menuController = menuController;
        this.types = types;
    }

    public void render() {
        System.out.println("Select a sudoku type:");
        for (String file : types) {
            System.out.println(file);
        }

        Scanner scanner = new Scanner(System.in);
        String input;
        while (scanner.hasNextLine()) {
            input = scanner.nextLine();
            if (types.contains(input)) {
                menuController.actionFileSelected(input);
            } else System.out.println("Invalid type, please try again.");
        }
    }
}
