package dev.kylian;

import dev.kylian.ui.TerminalView;

import java.io.PrintWriter;

public class App {

    public static void main(String[] args) {
        TerminalView terminalView = new TerminalView(new PrintWriter(System.out));
        terminalView.viewSudoku();
    }
}
