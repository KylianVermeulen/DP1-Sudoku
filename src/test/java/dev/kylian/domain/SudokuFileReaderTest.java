package dev.kylian.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SudokuFileReaderTest {
    private SudokuFileReader fileReader;
    private File testFile1;
    private File testFile2;

    @BeforeEach
    public void setUp() {
        fileReader = new SudokuFileReader();
        testFile1 = new File("src/main/resources/puzzle.4x4");
        testFile2 = new File("src/main/resources/puzzle.samurai");
    }

    @Test
    public void testReadFileToString() throws IOException {
        String expected = "0340400210030210";
        String actual = fileReader.readFileToString(testFile1);
        assertEquals(expected, actual);
    }

    @Test
    public void testReadFileToStrings() throws IOException {
        String[] expected = {"800000700003050206700300095000091840000007002000062000000000000609080000002903000", "149000000000091000000060000007120008000000340405008067000000000000007020000050003", "000000000000008000000004000010600005030070080800005010000900000000800000000000000", "900060000030400000000000000390800407065000000200037600000080000000190000000000914", "000402800000080902000000000000610000400800000098750000670008001901060700002000009"};
        String[] actual = fileReader.readFileToStrings(testFile2);
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }
}