package fr.fges.business;

import fr.fges.data.GameRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GetAllGamesNumberMatchLogic {
    private final GameRepository gameRepository;
    private final ViewAllGamesLogic viewAllGamesLogic;

    public GetAllGamesNumberMatchLogic(GameRepository gameRepository, ViewAllGamesLogic viewAllGamesLogic) {
        this.gameRepository = gameRepository;
        this.viewAllGamesLogic = viewAllGamesLogic;
    }

    public List<BoardGame> getAllGamesNumberMatch(int nbPlayer) {
        if (gameRepository.getAll().isEmpty()) {
            return Collections.emptyList();
        }
        List<BoardGame> allGamesMatch = new ArrayList<>();
        List<BoardGame> gamesSorted = viewAllGamesLogic.viewAllGames();
        for (BoardGame game : gamesSorted) {
            if (game.minPlayers() <= nbPlayer && game.maxPlayers() >= nbPlayer) {
                allGamesMatch.add(game);
            }
        }
        if (allGamesMatch.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            return allGamesMatch;
        }
    }
}
