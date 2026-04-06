package fr.fges.data;

import fr.fges.business.BoardGame;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JsonGameRepositoryTest {
    @TempDir
    Path tempDir;

    @Test
    void returnsEmptyListWhenFileDoesNotExist() {
        JsonGameRepository repository = new JsonGameRepository(tempDir.resolve("games.json").toString());

        assertTrue(repository.getAll().isEmpty());
    }

    @Test
    void saveAndReadRoundTripPreservesGames() {
        Path file = tempDir.resolve("games.json");
        JsonGameRepository repository = new JsonGameRepository(file.toString());
        List<BoardGame> games = List.of(
                new BoardGame("Azul", 2, 4, "abstract"),
                new BoardGame("Catan", 3, 4, "strategy")
        );

        repository.save(games);

        assertEquals(games, repository.getAll());
    }

    @Test
    void addAndRemovePersistChanges() {
        JsonGameRepository repository = new JsonGameRepository(tempDir.resolve("games.json").toString());
        BoardGame game = new BoardGame("Azul", 2, 4, "abstract");

        repository.add(game);
        assertEquals(List.of(game), repository.getAll());

        repository.remove(game);
        assertTrue(repository.getAll().isEmpty());
    }

    @Test
    void returnsEmptyListWhenJsonIsInvalid() throws IOException {
        Path file = tempDir.resolve("games.json");
        Files.writeString(file, "{not valid json");
        JsonGameRepository repository = new JsonGameRepository(file.toString());

        assertTrue(repository.getAll().isEmpty());
    }
}
