package fr.fges.business;

import fr.fges.support.InMemoryGameRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GetRandomGamesLogicTest {
    @Test
    void returnsEmptyListWhenRepositoryIsEmpty() {
        GetRandomGamesLogic logic = new GetRandomGamesLogic(new InMemoryGameRepository());

        assertTrue(logic.getRandomGames(3).isEmpty());
    }

    @Test
    void throwsWhenRequestExceedsAvailableGames() {
        GetRandomGamesLogic logic = new GetRandomGamesLogic(
                new InMemoryGameRepository(new BoardGame("Azul", 2, 4, "abstract"))
        );

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> logic.getRandomGames(2)
        );

        assertEquals("1", exception.getMessage());
    }

    @Test
    void returnsRequestedAmountWhenEnoughGamesExist() {
        BoardGame game = new BoardGame("Azul", 2, 4, "abstract");
        GetRandomGamesLogic logic = new GetRandomGamesLogic(new InMemoryGameRepository(game));

        List<BoardGame> result = logic.getRandomGames(1);

        assertEquals(1, result.size());
        assertEquals(game, result.getFirst());
    }
}
