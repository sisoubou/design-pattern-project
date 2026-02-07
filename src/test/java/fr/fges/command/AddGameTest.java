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

class   AddGameTest {


    @Test
    void addGame_addsAndSaves() {
        GameRepository mockRepo = Mockito.mock(GameRepository.class);
        Mockito.when(mockRepo.load()).thenReturn(new ArrayList<>());
        GameCollection collection = new GameCollection(mockRepo);
        BoardGame game = new BoardGame("Catan", 3, 4, "Board");
        collection.addGame(game);
        verify(mockRepo, times(1)).save(anyList());
        assertEquals(1, collection.getGames().size());
    }

    @Test
    void addGame_duplicateDoesNotAdd() {
        GameRepository mockRepo = Mockito.mock(GameRepository.class);
        Mockito.when(mockRepo.load()).thenReturn(new ArrayList<>());
        GameCollection collection = new GameCollection(mockRepo);
        BoardGame game = new BoardGame("Uno", 2, 10, "Card");
        collection.addGame(game);
        collection.addGame(new BoardGame("Uno", 2, 10, "Card"));
        verify(mockRepo, times(1)).save(anyList());
        assertEquals(1, collection.getGames().size());
    }

    @Test
    void addGame_onNonEmptyCollection() {
        List<BoardGame> initial = new ArrayList<>();
        initial.add(new BoardGame("Monopoly", 2, 6, "Board"));
        GameRepository mockRepo = Mockito.mock(GameRepository.class);
        Mockito.when(mockRepo.load()).thenReturn(initial);
        GameCollection collection = new GameCollection(mockRepo);
        BoardGame game = new BoardGame("Catan", 3, 4, "Board");
        collection.addGame(game);
        verify(mockRepo, times(1)).save(anyList());
        assertEquals(2, collection.getGames().size());
    }

    @Test
    void addGame_duplicate_doesNotSaveAgain() {
        GameRepository mockRepo = Mockito.mock(GameRepository.class);
        Mockito.when(mockRepo.load()).thenReturn(new ArrayList<>());

        GameCollection collection = new GameCollection(mockRepo);

        BoardGame first = new BoardGame("Uno", 2, 10, "Card");
        collection.addGame(first);

        BoardGame duplicate = new BoardGame("Uno", 2, 10, "Card");
        collection.addGame(duplicate);

        verify(mockRepo, times(1)).save(anyList());
        assertEquals(1, collection.getGames().size());
    }
}