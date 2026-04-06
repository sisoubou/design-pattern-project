package fr.fges.command;

import fr.fges.business.GameHistory;
import fr.fges.business.UndoLogic;
import fr.fges.support.ConsoleCapture;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class UndoCommandTest {
    @Test
    void executePrintsUndoneMessageWhenHistoryContainsAnAction() {
        GameHistory history = new GameHistory();
        history.push(() -> "Removed Azul from collection.");
        UndoCommand command = new UndoCommand(new UndoLogic(history));

        try (ConsoleCapture capture = new ConsoleCapture()) {
            command.execute();

            assertTrue(capture.stdout().contains("Undone : Removed Azul from collection."));
        }
    }

    @Test
    void executeShowsErrorWhenNothingCanBeUndone() {
        UndoCommand command = new UndoCommand(new UndoLogic(new GameHistory()));

        try (ConsoleCapture capture = new ConsoleCapture()) {
            command.execute();

            assertTrue(capture.stderr().contains("Nothing to undo."));
        }
    }
}
