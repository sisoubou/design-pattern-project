package fr.fges.samplecode;

import fr.fges.BoardGame;
import fr.fges.GameCollection;
import fr.fges.GameRepository;
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

class GameCollectionTests {


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

        // save should have been called only once (on first add)
        verify(mockRepo, times(1)).save(anyList());
        assertEquals(1, collection.getGames().size());
    }


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
        verify(mockRepo).save(anyList());
        assertEquals(1, collection.getGames().size());
    }

    @Test
    void removeGame_onEmptyCollection() {
        GameRepository mockRepo = Mockito.mock(GameRepository.class);
        Mockito.when(mockRepo.load()).thenReturn(new ArrayList<>());
        GameCollection collection = new GameCollection(mockRepo);
        BoardGame notInList = new BoardGame("Uno", 2, 10, "Card");
        collection.removeGame(notInList);
        verify(mockRepo).save(anyList());
        assertEquals(0, collection.getGames().size());
    }


    @Test
    void recommendGame_noMatch_printsMessage() {
        GameRepository mockRepo = Mockito.mock(GameRepository.class);
        Mockito.when(mockRepo.load()).thenReturn(Arrays.asList(new BoardGame("BigParty", 8, 12, "Party")));
        GameCollection collection = new GameCollection(mockRepo);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(out));
        collection.recommendGame(2);
        System.setOut(old);
        String output = out.toString();
        assertTrue(output.contains("no games matching"));
    }

    @Test
    void recommendGame_oneMatch_printsRecommendation() {
        BoardGame g = new BoardGame("Uno", 2, 4, "Card");
        GameRepository mockRepo = Mockito.mock(GameRepository.class);
        Mockito.when(mockRepo.load()).thenReturn(Arrays.asList(g));
        GameCollection collection = new GameCollection(mockRepo);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(out));
        collection.recommendGame(3);
        System.setOut(old);
        String output = out.toString();
        assertTrue(output.contains("Recommended Game"));
        assertTrue(output.contains("Uno"));
    }

    @Test
    void recommendGame_multipleMatches_printsRecommendation() {
        BoardGame g1 = new BoardGame("Uno", 2, 4, "Card");
        BoardGame g2 = new BoardGame("Catan", 3, 6, "Board");
        GameRepository mockRepo = Mockito.mock(GameRepository.class);
        Mockito.when(mockRepo.load()).thenReturn(Arrays.asList(g1, g2));
        GameCollection collection = new GameCollection(mockRepo);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(out));
        collection.recommendGame(3);
        System.setOut(old);
        String output = out.toString();
        assertTrue(output.contains("Recommended Game"));
        assertTrue(output.contains("Uno") || output.contains("Catan"));
    }


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
    void viewAllGames_emptyPrintsMessage() {
        GameRepository mockRepo = Mockito.mock(GameRepository.class);
        Mockito.when(mockRepo.load()).thenReturn(new ArrayList<>());
        GameCollection collection = new GameCollection(mockRepo);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(out));
        collection.viewAllGames();
        System.setOut(old);
        String output = out.toString();
        assertTrue(output.contains("No board games"));
    }

    @Test
    void viewAllGames_nonEmptyPrintsSorted() {
        BoardGame g1 = new BoardGame("Uno", 2, 4, "Card");
        BoardGame g2 = new BoardGame("Catan", 3, 6, "Board");
        GameRepository mockRepo = Mockito.mock(GameRepository.class);
        Mockito.when(mockRepo.load()).thenReturn(Arrays.asList(g1, g2));
        GameCollection collection = new GameCollection(mockRepo);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(out));
        collection.viewAllGames();
        System.setOut(old);
        String output = out.toString();
        // Sorted by title: Catan then Uno
        assertTrue(output.indexOf("Catan") < output.indexOf("Uno"));
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

