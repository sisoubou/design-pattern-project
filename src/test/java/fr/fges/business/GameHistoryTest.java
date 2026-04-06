package fr.fges.business;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameHistoryTest {
    @Test
    void pushAndPopWorkAsALifoStack() {
        GameHistory history = new GameHistory();

        history.push(() -> "first");
        history.push(() -> "second");

        assertFalse(history.isEmpty());
        assertEquals("second", history.pop().undo());
        assertEquals("first", history.pop().undo());
        assertTrue(history.isEmpty());
    }
}
