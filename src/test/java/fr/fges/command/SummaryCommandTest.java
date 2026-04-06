package fr.fges.command;

import fr.fges.business.BoardGame;
import fr.fges.business.GetRandomGamesLogic;
import fr.fges.support.ConsoleCapture;
import fr.fges.support.InMemoryGameRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SummaryCommandTest {
    @Test
    void executeShowsEmptyCollectionErrorWhenRepositoryIsEmpty() {
        SummaryCommand command = new SummaryCommand(new GetRandomGamesLogic(new InMemoryGameRepository()));

        try (ConsoleCapture capture = new ConsoleCapture()) {
            command.execute();

            assertTrue(capture.stderr().contains("No board games in collection."));
        }
    }

    @Test
    void executeDisplaysSummaryWhenEnoughGamesExist() {
        BoardGame game = new BoardGame("Azul", 2, 4, "abstract");
        SummaryCommand command = new SummaryCommand(new GetRandomGamesLogic(
                new InMemoryGameRepository(game, game, game)
        ));

        try (ConsoleCapture capture = new ConsoleCapture()) {
            command.execute();

            assertTrue(capture.stdout().contains("== Summary (3 random games) =="));
            assertTrue(capture.stdout().contains("Game: Azul (2-4 players) - abstract"));
        }
    }

    @Test
    void executeShowsNotEnoughGamesMessageWhenRandomSelectionCannotBeBuilt() {
        SummaryCommand command = new SummaryCommand(new GetRandomGamesLogic(
                new InMemoryGameRepository(new BoardGame("Azul", 2, 4, "abstract"))
        ));

        try (ConsoleCapture capture = new ConsoleCapture()) {
            command.execute();

            assertTrue(capture.stdout().contains("Not enough games in collection to get 1 random games."));
        }
    }
}
