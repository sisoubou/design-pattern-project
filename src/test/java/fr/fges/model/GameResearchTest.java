package fr.fges.samplecode;

import fr.fges.BoardGame;
import fr.fges.GameResearch;
import fr.fges.data.GameRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GameResearchTest {

    @Test
    void recommendGame_noMatch_returnsEmpty() {
        GameRepository mockRepo = Mockito.mock(GameRepository.class);
        Mockito.when(mockRepo.getAll()).thenReturn(Arrays.asList(new BoardGame("BigParty", 8, 12, "Party")));
        GameResearch research = new GameResearch(mockRepo);

        Optional<BoardGame> result = research.recommendGame(2);

        assertTrue(result.isEmpty(), "Should return empty when no games match.");
    }

    @Test
    void recommendGame_oneMatch_returnsGame() {
        BoardGame g = new BoardGame("Uno", 2, 4, "Card");
        GameRepository mockRepo = Mockito.mock(GameRepository.class);
        Mockito.when(mockRepo.getAll()).thenReturn(Arrays.asList(g));
        GameResearch research = new GameResearch(mockRepo);

        Optional<BoardGame> result = research.recommendGame(3);

        assertTrue(result.isPresent());
        assertEquals("Uno", result.get().title());
    }

    @Test
    void getRandomGames_countGreaterThanSize_returnsAll() {
        BoardGame g1 = new BoardGame("Uno", 2, 4, "Card");
        GameRepository mockRepo = Mockito.mock(GameRepository.class);
        Mockito.when(mockRepo.getAll()).thenReturn(Arrays.asList(g1));
        GameResearch research = new GameResearch(mockRepo);

        List<BoardGame> result = research.getRandomGames(2);

        assertEquals(1, result.size());
        assertEquals("Uno", result.get(0).title());
    }
}