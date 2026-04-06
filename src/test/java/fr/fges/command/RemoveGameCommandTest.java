package fr.fges.command;

import fr.fges.business.BoardGame;
import fr.fges.business.GameHistory;
import fr.fges.business.RemoveGameLogic;
import fr.fges.support.ConsoleCapture;
import fr.fges.support.InMemoryGameRepository;
import fr.fges.ui.UserInput;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RemoveGameCommandTest {
    @Test
    void executeRemovesMatchingGame() {
        BoardGame game = new BoardGame("Azul", 2, 4, "abstract");
        InMemoryGameRepository repository = new InMemoryGameRepository(game);
        RemoveGameCommand command = new RemoveGameCommand(
                new RemoveGameLogic(repository, new GameHistory()),
                new UserInput(new Scanner("Azul\n"))
        );

        try (ConsoleCapture capture = new ConsoleCapture()) {
            command.execute();

            assertTrue(repository.getAll().isEmpty());
            assertTrue(capture.stdout().contains("Board game removed successfully."));
        }
    }

    @Test
    void executeShowsMessageWhenGameDoesNotExist() {
        RemoveGameCommand command = new RemoveGameCommand(
                new RemoveGameLogic(new InMemoryGameRepository(), new GameHistory()),
                new UserInput(new Scanner("Azul\n"))
        );

        try (ConsoleCapture capture = new ConsoleCapture()) {
            command.execute();

            assertTrue(capture.stdout().contains("No board game found with that title."));
        }
    }
}
