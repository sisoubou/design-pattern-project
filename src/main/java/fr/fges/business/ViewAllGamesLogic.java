package fr.fges.business;

import fr.fges.data.GameRepository;
import java.util.Comparator;
import java.util.List;

public class ViewAllGamesLogic {
    private final GameRepository gameRepository;

    public ViewAllGamesLogic(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<BoardGame> viewAllGames() {
        return gameRepository.getAll().stream()
                .sorted(Comparator.comparing(BoardGame::title))
                .toList();
    }
}
