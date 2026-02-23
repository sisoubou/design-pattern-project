package fr.fges.command;

import fr.fges.BoardGame;
import fr.fges.GameCollection;
import fr.fges.data.GameRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class RemoveGamesTest {

    @Test
    void removeGame_callsRepositoryRemove() {
        BoardGame existing = new BoardGame("Catan", 3, 4, "Board");
        List<BoardGame> initial = new ArrayList<>();
        initial.add(existing);

        GameRepository mockRepo = Mockito.mock(GameRepository.class);
        Mockito.when(mockRepo.getAll()).thenReturn(initial);
        GameCollection collection = new GameCollection(mockRepo);

        collection.removeGame(existing);

        verify(mockRepo, times(1)).remove(existing);
    }
}