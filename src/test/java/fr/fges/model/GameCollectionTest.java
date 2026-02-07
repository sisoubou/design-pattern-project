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

class   GameCollectionTests {

    @Test
    void alreadyExist_trueIfExists() {
        GameRepository mockRepo = Mockito.mock(GameRepository.class);
        Mockito.when(mockRepo.load()).thenReturn(Arrays.asList(new BoardGame("Uno", 2, 4, "Card")));
        GameCollection collection = new GameCollection(mockRepo);
        BoardGame existing = new BoardGame("Uno", 2, 4, "Card");
        assertTrue(collection.alreadyExist(existing));
    }

    @Test
    void alreadyExist_falseIfNotExists() {
        GameRepository mockRepo = Mockito.mock(GameRepository.class);
        Mockito.when(mockRepo.load()).thenReturn(Arrays.asList(new BoardGame("Uno", 2, 4, "Card")));
        GameCollection collection = new GameCollection(mockRepo);
        BoardGame fresh = new BoardGame("NewGame", 1, 2, "Mini");
        assertFalse(collection.alreadyExist(fresh));
    }

    @Test
    void alreadyExist_emptyCollection() {
        GameRepository mockRepo = Mockito.mock(GameRepository.class);
        Mockito.when(mockRepo.load()).thenReturn(new ArrayList<>());
        GameCollection collection = new GameCollection(mockRepo);
        BoardGame fresh = new BoardGame("NewGame", 1, 2, "Mini");
        assertFalse(collection.alreadyExist(fresh));
    }

    @Test
    void getGames_emptyAndNonEmpty() {
        GameRepository mockRepo = Mockito.mock(GameRepository.class);
        Mockito.when(mockRepo.load()).thenReturn(new ArrayList<>());
        GameCollection collection = new GameCollection(mockRepo);
        assertTrue(collection.getGames().isEmpty());
        collection.addGame(new BoardGame("Uno", 2, 4, "Card"));
        assertFalse(collection.getGames().isEmpty());
    }

    @Test
    void getRandomGames_emptyPrintsMessage() {
        GameRepository mockRepo = Mockito.mock(GameRepository.class);
        Mockito.when(mockRepo.load()).thenReturn(new ArrayList<>());
        GameCollection collection = new GameCollection(mockRepo);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(out));
        collection.getRandomGames(2);
        System.setOut(old);
        String output = out.toString();
        assertTrue(output.contains("No board games"));
    }

    @Test
    void getRandomGames_countGreaterThanSizePrintsMessage() {
        BoardGame g1 = new BoardGame("Uno", 2, 4, "Card");
        GameRepository mockRepo = Mockito.mock(GameRepository.class);
        Mockito.when(mockRepo.load()).thenReturn(Arrays.asList(g1));
        GameCollection collection = new GameCollection(mockRepo);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(out));
        collection.getRandomGames(2);
        System.setOut(old);
        String output = out.toString();
        assertTrue(output.contains("Not enough games"));
    }

    @Test
    void getRandomGames_countEqualsSizePrintsSummary() {
        BoardGame g1 = new BoardGame("Uno", 2, 4, "Card");
        BoardGame g2 = new BoardGame("Catan", 3, 6, "Board");
        GameRepository mockRepo = Mockito.mock(GameRepository.class);
        Mockito.when(mockRepo.load()).thenReturn(Arrays.asList(g1, g2));
        GameCollection collection = new GameCollection(mockRepo);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(out));
        collection.getRandomGames(2);
        System.setOut(old);
        String output = out.toString();
        assertTrue(output.contains("== Summary"));
        assertTrue(output.contains("Uno"));
        assertTrue(output.contains("Catan"));
    }

    @Test
    void getRandomGames_countLessThanSizePrintsSummary() {
        BoardGame g1 = new BoardGame("Uno", 2, 4, "Card");
        BoardGame g2 = new BoardGame("Catan", 3, 6, "Board");
        BoardGame g3 = new BoardGame("Monopoly", 2, 6, "Board");
        GameRepository mockRepo = Mockito.mock(GameRepository.class);
        Mockito.when(mockRepo.load()).thenReturn(Arrays.asList(g1, g2, g3));
        GameCollection collection = new GameCollection(mockRepo);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(out));
        collection.getRandomGames(2);
        System.setOut(old);
        String output = out.toString();
        assertTrue(output.contains("== Summary"));
        // Peut contenir n'importe quel jeu, on vérifie juste le format
        assertTrue(output.contains("players"));
    }
}

