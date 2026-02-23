package fr.fges.command;

import fr.fges.BoardGame;
import fr.fges.GameCollection;
import fr.fges.data.GameRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AddGameTest {

    @Test
    void addGame_addsToRepository() {
        GameRepository mockRepo = Mockito.mock(GameRepository.class);
        Mockito.when(mockRepo.getAll()).thenReturn(new ArrayList<>());
        GameCollection collection = new GameCollection(mockRepo);
        BoardGame game = new BoardGame("Catan", 3, 4, "Board");

        collection.addGame(game);

        verify(mockRepo, times(1)).add(game);
    }

    @Test
    void addGame_duplicateThrowsException() {
        GameRepository mockRepo = Mockito.mock(GameRepository.class);
        BoardGame game = new BoardGame("Uno", 2, 10, "Card");
        Mockito.when(mockRepo.getAll()).thenReturn(List.of(game));
        GameCollection collection = new GameCollection(mockRepo);

        assertThrows(IllegalArgumentException.class, () -> collection.addGame(game));

        verify(mockRepo, never()).add(any());
    }
}