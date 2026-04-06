package fr.fges.business;

import fr.fges.support.InMemoryGameRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ViewAllGamesLogicTest {
    @Test
    void sortsGamesAlphabeticallyByTitle() {
        BoardGame catan = new BoardGame("Catan", 3, 4, "strategy");
        BoardGame azul = new BoardGame("Azul", 2, 4, "abstract");
        ViewAllGamesLogic logic = new ViewAllGamesLogic(new InMemoryGameRepository(catan, azul));

        assertEquals(List.of(azul, catan), logic.viewAllGames());
    }
}
