package dev.kylian;

import dev.kylian.ui.TerminalView;

import java.io.PrintWriter;

public class App {

    public static void main(String[] args) {
        PrintWriter printWriter = new PrintWriter(System.out);
        TerminalView terminalView = new TerminalView(printWriter);
        terminalView.viewSudoku();
    }
}
