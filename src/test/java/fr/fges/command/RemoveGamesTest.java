package fr.fges.samplecode;

import fr.fges.BoardGame;
import fr.fges.GameCollection;
import fr.fges.data.GameRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class   RemoveGamesTest {

    @Test
    void removeGame_existingRemovesAndSaves() {
        BoardGame existing = new BoardGame("Catan", 3, 4, "Board");
        List<BoardGame> initial = new ArrayList<>();
        initial.add(existing);
        GameRepository mockRepo = Mockito.mock(GameRepository.class);
        Mockito.when(mockRepo.load()).thenReturn(initial);
        GameCollection collection = new GameCollection(mockRepo);
        collection.removeGame(existing);
        @SuppressWarnings("unchecked")
        ArgumentCaptor<List<BoardGame>> captor = ArgumentCaptor.forClass(List.class);
        verify(mockRepo).save(captor.capture());
        List<BoardGame> saved = captor.getValue();
        assertNotNull(saved);
        assertEquals(0, saved.size());
        assertEquals(0, collection.getGames().size());
    }

    @Test
    void removeGame_nonExistingDoesNothing() {
        BoardGame existing = new BoardGame("Catan", 3, 4, "Board");
        List<BoardGame> initial = new ArrayList<>();
        initial.add(existing);
        GameRepository mockRepo = Mockito.mock(GameRepository.class);
        Mockito.when(mockRepo.load()).thenReturn(initial);
        GameCollection collection = new GameCollection(mockRepo);
        BoardGame notInList = new BoardGame("Uno", 2, 10, "Card");
        collection.removeGame(notInList);
        verify(mockRepo, never()).save(anyList());
        assertEquals(1, collection.getGames().size());
    }

    @Test
    void removeGame_onEmptyCollection() {
        GameRepository mockRepo = Mockito.mock(GameRepository.class);
        Mockito.when(mockRepo.load()).thenReturn(new ArrayList<>());
        GameCollection collection = new GameCollection(mockRepo);
        BoardGame notInList = new BoardGame("Uno", 2, 10, "Card");
        collection.removeGame(notInList);
        verify(mockRepo, never()).save(anyList());
        assertEquals(0, collection.getGames().size());
    }

}

