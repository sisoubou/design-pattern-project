package fr.fges.business;

import fr.fges.support.InMemoryGameRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddGameLogicTest {
    @Test
    void addGameAddsTheGameAndRegistersUndoAction() {
        InMemoryGameRepository repository = new InMemoryGameRepository();
        GameHistory history = new GameHistory();
        AddGameLogic logic = new AddGameLogic(repository, history);
        BoardGame game = new BoardGame("Catan", 3, 4, "strategy");

        logic.addGame(game);

        assertEquals(1, repository.getAll().size());
        assertEquals(game, repository.getAll().getFirst());
        assertFalse(history.isEmpty());

        String undoMessage = history.pop().undo();

        assertTrue(repository.getAll().isEmpty());
        assertEquals("Removed Catan from collection.", undoMessage);
    }

    @Test
    void addGameRejectsExistingTitle() {
        InMemoryGameRepository repository = new InMemoryGameRepository(
                new BoardGame("Catan", 3, 4, "strategy")
        );
        AddGameLogic logic = new AddGameLogic(repository, new GameHistory());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> logic.addGame(new BoardGame("Catan", 2, 5, "family"))
        );

        assertEquals("Catan", exception.getMessage());
    }

    @Test
    void alreadyExistComparesOnlyTitles() {
        InMemoryGameRepository repository = new InMemoryGameRepository(
                new BoardGame("Azul", 2, 4, "abstract")
        );
        AddGameLogic logic = new AddGameLogic(repository, new GameHistory());

        assertTrue(logic.alreadyExist(new BoardGame("Azul", 1, 6, "party")));
        assertFalse(logic.alreadyExist(new BoardGame("7 Wonders", 3, 7, "card")));
    }
}
