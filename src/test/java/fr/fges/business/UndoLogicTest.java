package fr.fges.business;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UndoLogicTest {
    @Test
    void undoThrowsWhenNoActionExists() {
        UndoLogic logic = new UndoLogic(new GameHistory());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, logic::undo);

        assertEquals("Nothing to undo.", exception.getMessage());
    }

    @Test
    void undoExecutesLastAction() {
        GameHistory history = new GameHistory();
        history.push(() -> "last action");
        UndoLogic logic = new UndoLogic(history);

        assertEquals("last action", logic.undo());
    }
}
