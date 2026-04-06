package fr.fges.command;

import fr.fges.business.AddGameLogic;
import fr.fges.business.BoardGame;
import fr.fges.business.GameHistory;
import fr.fges.support.ConsoleCapture;
import fr.fges.support.InMemoryGameRepository;
import fr.fges.ui.UserInput;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddGameCommandTest {
    @Test
    void executeAddsGameAndPrintsSuccessMessage() {
        InMemoryGameRepository repository = new InMemoryGameRepository();
        AddGameCommand command = new AddGameCommand(
                new AddGameLogic(repository, new GameHistory()),
                new UserInput(new Scanner("Azul\n2\n4\nabstract\n"))
        );

        try (ConsoleCapture capture = new ConsoleCapture()) {
            command.execute();

            assertEquals(1, repository.getAll().size());
            assertEquals("Azul", repository.getAll().getFirst().title());
            assertTrue(capture.stdout().contains("Azul has been added successfully"));
        }
    }

    @Test
    void executeShowsErrorWhenGameAlreadyExists() {
        InMemoryGameRepository repository = new InMemoryGameRepository(
                new BoardGame("Azul", 2, 4, "abstract")
        );
        AddGameCommand command = new AddGameCommand(
                new AddGameLogic(repository, new GameHistory()),
                new UserInput(new Scanner("Azul\n2\n4\nabstract\n"))
        );

        try (ConsoleCapture capture = new ConsoleCapture()) {
            command.execute();

            assertEquals(1, repository.getAll().size());
            assertTrue(capture.stderr().contains("Error: A game with title 'Azul' already exists"));
        }
    }
}
