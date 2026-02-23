package fr.fges.samplecode;

import fr.fges.BoardGame;
import fr.fges.GameCollection;
import fr.fges.data.GameRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GameCollectionTest {

    @Test
    void alreadyExist_trueIfExists() {
        GameRepository mockRepo = Mockito.mock(GameRepository.class);
        Mockito.when(mockRepo.getAll()).thenReturn(Arrays.asList(new BoardGame("Uno", 2, 4, "Card")));
        GameCollection collection = new GameCollection(mockRepo);
        BoardGame existing = new BoardGame("Uno", 2, 4, "Card");
        assertTrue(collection.alreadyExist(existing));
    }

    @Test
    void alreadyExist_falseIfNotExists() {
        GameRepository mockRepo = Mockito.mock(GameRepository.class);
        Mockito.when(mockRepo.getAll()).thenReturn(Arrays.asList(new BoardGame("Uno", 2, 4, "Card")));
        GameCollection collection = new GameCollection(mockRepo);
        BoardGame fresh = new BoardGame("NewGame", 1, 2, "Mini");
        assertFalse(collection.alreadyExist(fresh));
    }

    @Test
    void alreadyExist_emptyCollection() {
        GameRepository mockRepo = Mockito.mock(GameRepository.class);
        Mockito.when(mockRepo.getAll()).thenReturn(new ArrayList<>());
        GameCollection collection = new GameCollection(mockRepo);
        BoardGame fresh = new BoardGame("NewGame", 1, 2, "Mini");
        assertFalse(collection.alreadyExist(fresh));
    }

    @Test
    void getGames_empty() {
        GameRepository mockRepo = Mockito.mock(GameRepository.class);
        Mockito.when(mockRepo.getAll()).thenReturn(new ArrayList<>());
        GameCollection collection = new GameCollection(mockRepo);
        assertTrue(collection.getGames().isEmpty());
    }
}