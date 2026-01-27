package fr.fges.samplecode;

import fr.fges.BoardGame;
import fr.fges.GameCollection;
import fr.fges.GameRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameCollectionTest {
    @Test
    void testAddGame(){
        GameRepository mockRepo = Mockito.mock(GameRepository.class);
        Mockito.when(mockRepo.load()).thenReturn(new ArrayList<>());
        GameCollection collection = new GameCollection(mockRepo);

        BoardGame myGame = new BoardGame("Uno", 2, 10, "Card");
        collection.addGame(myGame);

        BoardGame mySecondGame = new BoardGame("Uno", 2, 10, "Card");
        collection.addGame(mySecondGame);

        assertEquals(1,collection.getGames().size(), "La collection devrait contenir 1 jeu");
    }

    @Test
    void testRemoveGame() {
        GameRepository mockRepo = Mockito.mock(GameRepository.class);

        BoardGame existingGame = new BoardGame("Uno", 2, 10, "Card");
        List<BoardGame> fakeList = new ArrayList<>();
        fakeList.add(existingGame);

        Mockito.when(mockRepo.load()).thenReturn(fakeList);
        GameCollection collection = new GameCollection(mockRepo);

        collection.removeGame(existingGame);

        assertEquals(0,collection.getGames().size(), "La collection devrait etre vide");
    }

}
