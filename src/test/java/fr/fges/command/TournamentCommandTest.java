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

class TournamentCommandTest {
    @Test
    void executeShowsErrorWhenNoTwoPlayerGameIsAvailable() {
        InMemoryGameRepository repository = new InMemoryGameRepository(
                new BoardGame("Final Girl", 1, 1, "solo")
        );
        TournamentCommand command = new TournamentCommand(
                new GetAllGamesNumberMatchLogic(repository, new ViewAllGamesLogic(repository)),
                new UserInput(new Scanner(""))
        );

        try (ConsoleCapture capture = new ConsoleCapture()) {
            command.execute();

            assertTrue(capture.stderr().contains("No games available for 2 players. Cannot organize a tournament."));
        }
    }

    @Test
    void executeRunsTournamentAndPrintsResults() {
        InMemoryGameRepository repository = new InMemoryGameRepository(
                new BoardGame("7 Wonders Duel", 2, 2, "duel")
        );
        TournamentCommand command = new TournamentCommand(
                new GetAllGamesNumberMatchLogic(repository, new ViewAllGamesLogic(repository)),
                new UserInput(new Scanner("1\n3\nAlice\nBob\nChloe\n1\n1\n1\n1\n"))
        );

        try (ConsoleCapture capture = new ConsoleCapture()) {
            command.execute();

            String stdout = capture.stdout();
            assertTrue(stdout.contains("=== Tournament Mode ==="));
            assertTrue(stdout.contains("Available 2-player games:"));
            assertTrue(stdout.contains("=== Tournament Results ==="));
            assertTrue(stdout.contains("1. Alice - 6 points (2 wins)"));
        }
    }
}
