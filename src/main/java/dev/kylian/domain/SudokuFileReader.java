package dev.kylian.domain;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class SudokuFileReader {

    public String readFileToString(File file) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (BufferedReader br = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8)) {

            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                contentBuilder.append(currentLine);
            }
        }

        return contentBuilder.toString();
    }

    public String[] readFileToStrings(File file) throws IOException {
        String[] lines = new String[5];

        try (BufferedReader br = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8)) {
            int i = 0;
            String currentLine;
            while ((currentLine = br.readLine()) != null && i < 5) {
                lines[i] = currentLine;
                i++;
            }
        }
        return lines;
    }
}
