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

class   RecommendGameTest {

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
}

