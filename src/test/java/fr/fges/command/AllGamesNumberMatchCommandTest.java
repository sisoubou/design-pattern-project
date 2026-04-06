package fr.fges.command;

import fr.fges.business.BoardGame;
import fr.fges.business.GetAllGamesNumberMatchLogic;
import fr.fges.business.ViewAllGamesLogic;
import fr.fges.support.ConsoleCapture;
import fr.fges.support.InMemoryGameRepository;
import fr.fges.ui.UserInput;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AllGamesNumberMatchCommandTest {
    @Test
    void executeShowsEmptyCollectionErrorWhenRepositoryIsEmpty() {
        InMemoryGameRepository repository = new InMemoryGameRepository();
        AllGamesNumberMatchCommand command = new AllGamesNumberMatchCommand(
                new GetAllGamesNumberMatchLogic(repository, new ViewAllGamesLogic(repository)),
                new UserInput(new Scanner("2\n"))
        );

        try (ConsoleCapture capture = new ConsoleCapture()) {
            command.execute();

            assertTrue(capture.stderr().contains("No board games in collection."));
        }
    }

    @Test
    void executeDisplaysMatchingGames() {
        InMemoryGameRepository repository = new InMemoryGameRepository(
                new BoardGame("Azul", 2, 4, "abstract"),
                new BoardGame("Final Girl", 1, 1, "solo")
        );
        AllGamesNumberMatchCommand command = new AllGamesNumberMatchCommand(
                new GetAllGamesNumberMatchLogic(repository, new ViewAllGamesLogic(repository)),
                new UserInput(new Scanner("2\n"))
        );

        try (ConsoleCapture capture = new ConsoleCapture()) {
            command.execute();

            assertTrue(capture.stdout().contains("Game: Azul (2-4 players) - abstract"));
        }
    }

    @Test
    void executeShowsNoMatchErrorWhenNothingFitsRequestedPlayerCount() {
        InMemoryGameRepository repository = new InMemoryGameRepository(
                new BoardGame("Final Girl", 1, 1, "solo")
        );
        AllGamesNumberMatchCommand command = new AllGamesNumberMatchCommand(
                new GetAllGamesNumberMatchLogic(repository, new ViewAllGamesLogic(repository)),
                new UserInput(new Scanner("4\n"))
        );

        try (ConsoleCapture capture = new ConsoleCapture()) {
            command.execute();

            assertTrue(capture.stderr().contains("There are no games matching your request"));
        }
    }
}
