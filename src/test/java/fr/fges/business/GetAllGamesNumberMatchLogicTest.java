package fr.fges.business;

import fr.fges.support.InMemoryGameRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GetAllGamesNumberMatchLogicTest {
    @Test
    void returnsEmptyListWhenRepositoryIsEmpty() {
        InMemoryGameRepository repository = new InMemoryGameRepository();
        GetAllGamesNumberMatchLogic logic = new GetAllGamesNumberMatchLogic(
                repository,
                new ViewAllGamesLogic(repository)
        );

        assertTrue(logic.getAllGamesNumberMatch(4).isEmpty());
    }

    @Test
    void returnsOnlyMatchingGamesSortedByTitle() {
        BoardGame codenames = new BoardGame("Codenames", 2, 8, "party");
        BoardGame azul = new BoardGame("Azul", 2, 4, "abstract");
        BoardGame solo = new BoardGame("Final Girl", 1, 1, "solo");
        InMemoryGameRepository repository = new InMemoryGameRepository(codenames, solo, azul);
        GetAllGamesNumberMatchLogic logic = new GetAllGamesNumberMatchLogic(
                repository,
                new ViewAllGamesLogic(repository)
        );

        List<BoardGame> result = logic.getAllGamesNumberMatch(2);

        assertEquals(List.of(azul, codenames), result);
    }

    @Test
    void throwsWhenNoGameMatchesRequestedPlayerCount() {
        InMemoryGameRepository repository = new InMemoryGameRepository(
                new BoardGame("Final Girl", 1, 1, "solo")
        );
        GetAllGamesNumberMatchLogic logic = new GetAllGamesNumberMatchLogic(
                repository,
                new ViewAllGamesLogic(repository)
        );

        assertThrows(IllegalArgumentException.class, () -> logic.getAllGamesNumberMatch(4));
    }
}
