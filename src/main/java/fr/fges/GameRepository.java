package fr.fges;

import java.util.List;

public interface GameRepository {
    List<BoardGame> load();

    void save(List<BoardGame> games);
}
