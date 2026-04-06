package fr.fges.ui;

import fr.fges.command.Command;
import fr.fges.support.ConsoleCapture;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MenuTest {
    @Test
    void invalidTextChoiceShowsErrorBeforeExecutingAValidCommand() {
        AtomicInteger executions = new AtomicInteger();
        Menu menu = new Menu(List.of(new ThrowingCommand("Add", executions)), new Scanner("foo\n1\n"));

        try (ConsoleCapture capture = new ConsoleCapture()) {
            assertThrows(StopMenu.class, menu::run);

            assertEquals(1, executions.get());
            assertTrue(capture.stdout().contains("Invalid choice. Please select a valid option."));
            assertTrue(capture.stdout().contains("1. Add"));
        }
    }

    @Test
    void outOfRangeChoiceShowsErrorBeforeExecutingAValidCommand() {
        AtomicInteger executions = new AtomicInteger();
        Menu menu = new Menu(List.of(new ThrowingCommand("List", executions)), new Scanner("9\n1\n"));

        try (ConsoleCapture capture = new ConsoleCapture()) {
            assertThrows(StopMenu.class, menu::run);

            assertEquals(1, executions.get());
            assertTrue(capture.stdout().contains("Invalid choice. Please select a valid option."));
            assertTrue(capture.stdout().contains("1. List"));
        }
    }

    private static class ThrowingCommand implements Command {
        private final String description;
        private final AtomicInteger executions;

        private ThrowingCommand(String description, AtomicInteger executions) {
            this.description = description;
            this.executions = executions;
        }

        @Override
        public void execute() {
            executions.incrementAndGet();
            throw new StopMenu();
        }

        @Override
        public String getDescription() {
            return description;
        }
    }

    private static class StopMenu extends RuntimeException {
    }
}
