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

class CsvGameRepositoryTest {
    @TempDir
    Path tempDir;

    @Test
    void returnsEmptyListWhenFileDoesNotExist() {
        CsvGameRepository repository = new CsvGameRepository(tempDir.resolve("games.csv").toString());

        assertTrue(repository.getAll().isEmpty());
    }

    @Test
    void saveAndReadRoundTripPreservesGames() throws IOException {
        Path file = tempDir.resolve("games.csv");
        CsvGameRepository repository = new CsvGameRepository(file.toString());
        List<BoardGame> games = List.of(
                new BoardGame("Azul", 2, 4, "abstract"),
                new BoardGame("Catan", 3, 4, "strategy")
        );

        repository.save(games);

        assertEquals(games, repository.getAll());
        assertTrue(Files.readString(file).startsWith("title,minPlayers,maxPlayers,category"));
    }

    @Test
    void addAndRemovePersistChanges() {
        CsvGameRepository repository = new CsvGameRepository(tempDir.resolve("games.csv").toString());
        BoardGame game = new BoardGame("Azul", 2, 4, "abstract");

        repository.add(game);
        assertEquals(List.of(game), repository.getAll());

        repository.remove(game);
        assertTrue(repository.getAll().isEmpty());
    }

    @Test
    void ignoresMalformedRowsWithMissingColumns() throws IOException {
        Path file = tempDir.resolve("games.csv");
        Files.writeString(
                file,
                "title,minPlayers,maxPlayers,category\nBroken,1\nAzul,2,4,abstract\n"
        );
        CsvGameRepository repository = new CsvGameRepository(file.toString());

        assertEquals(List.of(new BoardGame("Azul", 2, 4, "abstract")), repository.getAll());
    }
}
