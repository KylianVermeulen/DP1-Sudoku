package dev.kylian.ui;

import dev.kylian.controller.MenuController;

import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Set;

public class FileSelectionView {
    private final MenuController menuController;
    private final Set<String> types;
    private final PrintWriter printWriter;

    public FileSelectionView(MenuController menuController, Set<String> types, PrintWriter printWriter) {
        this.menuController = menuController;
        this.types = types;
        this.printWriter = printWriter;
    }

    public void render() {
        printWriter.println("Select a sudoku type:");
        printWriter.flush();
        for (String file : types) {
            System.out.println(file);
        }

        Scanner scanner = new Scanner(System.in);
        String input;
        while (scanner.hasNextLine()) {
            input = scanner.nextLine();
            if (types.contains(input)) {
                menuController.actionFileSelected(input);
            } else {
                printWriter.println("Select a sudoku type:");
                printWriter.flush();
            }
        }
    }
}
