package dev.kylian.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditorModeTest {

    @Test
    public void testEnumValues() {
        // There are only two values in the enum
        assertEquals(2, EditorMode.values().length);

        // The first value is HELP_NUMBER
        assertEquals(EditorMode.HELP_NUMBER, EditorMode.values()[0]);

        // The second value is FINAL_NUMBER
        assertEquals(EditorMode.FINAL_NUMBER, EditorMode.values()[1]);
    }
}