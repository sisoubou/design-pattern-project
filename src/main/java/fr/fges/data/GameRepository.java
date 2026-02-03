package fr.fges.data;

import fr.fges.BoardGame;

import java.util.List;

public interface GameRepository {
    List<BoardGame> load();

    void save(List<BoardGame> games);
}
