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

class   ViewAllGameTest {

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
}

