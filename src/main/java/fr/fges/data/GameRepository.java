package fr.fges.data;

import fr.fges.BoardGame;

import java.util.List;

public interface GameRepository {
    List<BoardGame> getAll();
    void add(BoardGame game);
    void remove(BoardGame game);
}
