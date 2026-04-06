package fr.fges.ui;

import fr.fges.support.ConsoleCapture;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserInputTest {
    @Test
    void getUserInputPrintsPromptAndReturnsTypedValue() {
        UserInput userInput = new UserInput(new Scanner("Azul\n"));

        try (ConsoleCapture capture = new ConsoleCapture()) {
            String value = userInput.getUserInput("Title");

            assertEquals("Azul", value);
            assertTrue(capture.stdout().contains("Title: "));
        }
    }
}
