package dev.kylian.ui;

import java.io.PrintWriter;

public class TerminalView {
    private final PrintWriter printWriter;

    public TerminalView(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }

    public void viewSudoku() {
        printWriter.println("[-][1][2] [-][-][-] [-][-][-]");
        printWriter.println("[3][-][-] [-][-][-] [-][-][-]");
        printWriter.println("[-][4][5] [-][-][-] [-][-][-]");
        printWriter.println("");
        printWriter.println("[-][-][-] [-][-][-] [-][-][-]");
        printWriter.println("[-][-][-] [-][-][-] [-][-][-]");
        printWriter.println("[-][-][-] [-][-][-] [-][-][-]");
        printWriter.println("");
        printWriter.println("[-][-][-] [-][-][-] [-][-][-]");
        printWriter.println("[-][-][-] [-][-][-] [-][-][-]");
        printWriter.println("[-][-][-] [-][-][-] [-][-][-]");
        printWriter.flush();
    }
}
