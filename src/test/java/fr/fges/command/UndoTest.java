
package fr.fges.command;


import fr.fges.BoardGame;
import fr.fges.GameCollection;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UndoTest {
    @Test
    void undoRestoresPreviousStateWithMockito() {
        GameCollection collection = mock(GameCollection.class);
        BoardGame game = new BoardGame("Catan", 3, 4, "Board");
        CommandHistory history = new CommandHistory();
        UndoAction action = mock(UndoAction.class);
        when(action.undo()).thenAnswer(invocation -> {
            collection.addGame(game);
            return "Undo remove";
        });
        history.push(action);
        GameUI ui = mock(GameUI.class);
        history.undo(ui);
        verify(action, times(1)).undo();
        verify(collection, times(1)).addGame(game);
    }

    @Test
    void undoShowsErrorIfNothingToUndo() {
        CommandHistory history = new CommandHistory();
        GameUI ui = mock(GameUI.class);
        history.undo(ui);
        verify(ui).showError("Nothing to undo.");
    }

    @Test
    void undoPrintsResultMessage() {
        CommandHistory history = new CommandHistory();
        UndoAction action = mock(UndoAction.class);
        when(action.undo()).thenReturn("Action undone");
        history.push(action);
        GameUI ui = mock(GameUI.class);
        // Capture la sortie standard
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        history.undo(ui);
        System.setOut(System.out);
        assertTrue(out.toString().contains("Undone : Action undone"));
    }
}
