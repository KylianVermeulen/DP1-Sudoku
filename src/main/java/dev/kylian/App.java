package dev.kylian;

import dev.kylian.controller.MenuController;

import java.io.PrintWriter;

public class App {

    public static void main(String[] args) {
        PrintWriter printWriter = new PrintWriter(System.out);
        new MenuController(printWriter);
    }
}
