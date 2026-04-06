package fr.fges.support;

import fr.fges.business.BoardGame;
import fr.fges.data.GameRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InMemoryGameRepository implements GameRepository {
    private final List<BoardGame> games = new ArrayList<>();

    public InMemoryGameRepository(BoardGame... initialGames) {
        games.addAll(Arrays.asList(initialGames));
    }

    @Override
    public List<BoardGame> getAll() {
        return new ArrayList<>(games);
    }

    @Override
    public void add(BoardGame game) {
        games.add(game);
    }

    @Override
    public void remove(BoardGame game) {
        games.remove(game);
    }
}
