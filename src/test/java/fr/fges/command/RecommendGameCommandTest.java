package fr.fges.command;

import fr.fges.business.BoardGame;
import fr.fges.business.RecommendGameLogic;
import fr.fges.support.ConsoleCapture;
import fr.fges.support.InMemoryGameRepository;
import fr.fges.ui.UserInput;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RecommendGameCommandTest {
    @Test
    void executeDisplaysRecommendedGameWhenMatchExists() {
        InMemoryGameRepository repository = new InMemoryGameRepository(
                new BoardGame("Azul", 2, 4, "abstract"),
                new BoardGame("Final Girl", 1, 1, "solo")
        );
        RecommendGameCommand command = new RecommendGameCommand(
                new RecommendGameLogic(repository),
                new UserInput(new Scanner("2\n"))
        );

        try (ConsoleCapture capture = new ConsoleCapture()) {
            command.execute();

            assertTrue(capture.stdout().contains("Recommended Game: Azul (2-4 players) - abstract"));
        }
    }

    @Test
    void executeShowsErrorWhenNoMatchExists() {
        InMemoryGameRepository repository = new InMemoryGameRepository(
                new BoardGame("Final Girl", 1, 1, "solo")
        );
        RecommendGameCommand command = new RecommendGameCommand(
                new RecommendGameLogic(repository),
                new UserInput(new Scanner("4\n"))
        );

        try (ConsoleCapture capture = new ConsoleCapture()) {
            command.execute();

            assertTrue(capture.stderr().contains("There are no games matching your request"));
        }
    }
}
