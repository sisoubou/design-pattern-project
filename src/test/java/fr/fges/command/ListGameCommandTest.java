package fr.fges.command;

import fr.fges.business.BoardGame;
import fr.fges.business.ViewAllGamesLogic;
import fr.fges.support.ConsoleCapture;
import fr.fges.support.InMemoryGameRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ListGameCommandTest {
    @Test
    void executeDisplaysGamesSortedByTitle() {
        InMemoryGameRepository repository = new InMemoryGameRepository(
                new BoardGame("Catan", 3, 4, "strategy"),
                new BoardGame("Azul", 2, 4, "abstract")
        );
        ListGameCommand command = new ListGameCommand(new ViewAllGamesLogic(repository));

        try (ConsoleCapture capture = new ConsoleCapture()) {
            command.execute();

            String stdout = capture.stdout();
            assertTrue(stdout.indexOf("Game: Azul") < stdout.indexOf("Game: Catan"));
        }
    }
}
