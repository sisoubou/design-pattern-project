package fr.fges.business;

import fr.fges.support.InMemoryGameRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RemoveGameLogicTest {
    @Test
    void removeGameDeletesTheGameAndRegistersUndoAction() {
        BoardGame game = new BoardGame("Catan", 3, 4, "strategy");
        InMemoryGameRepository repository = new InMemoryGameRepository(game);
        GameHistory history = new GameHistory();
        RemoveGameLogic logic = new RemoveGameLogic(repository, history);

        logic.removeGame(game);

        assertTrue(repository.getAll().isEmpty());
        assertEquals("Added Catan back to the collection.", history.pop().undo());
        assertEquals(List.of(game), repository.getAll());
    }

    @Test
    void getGamesReturnsRepositoryContent() {
        BoardGame game = new BoardGame("Azul", 2, 4, "abstract");
        RemoveGameLogic logic = new RemoveGameLogic(
                new InMemoryGameRepository(game),
                new GameHistory()
        );

        assertEquals(List.of(game), logic.getGames());
    }
}
