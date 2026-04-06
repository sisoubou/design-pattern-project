package fr.fges.business;

import fr.fges.support.InMemoryGameRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RecommendGameLogicTest {
    @Test
    void returnsEmptyWhenNoGameMatches() {
        RecommendGameLogic logic = new RecommendGameLogic(
                new InMemoryGameRepository(new BoardGame("Final Girl", 1, 1, "solo"))
        );

        assertTrue(logic.recommendGame(4).isEmpty());
    }

    @Test
    void recommendsAMatchingGame() {
        BoardGame game = new BoardGame("Azul", 2, 4, "abstract");
        RecommendGameLogic logic = new RecommendGameLogic(
                new InMemoryGameRepository(game, new BoardGame("Final Girl", 1, 1, "solo"))
        );

        Optional<BoardGame> recommendation = logic.recommendGame(2);

        assertEquals(Optional.of(game), recommendation);
    }
}
