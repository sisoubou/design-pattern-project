package fr.fges.business;

import fr.fges.data.GameRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class RecommendGameLogic {
    private final GameRepository gameRepository;

    public RecommendGameLogic(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Optional<BoardGame> recommendGame(int nbPlayer) {
        List<BoardGame> matchingGames = new ArrayList<>();
        for (BoardGame game : gameRepository.getAll()) {
            if (game.minPlayers() <= nbPlayer && game.maxPlayers() >= nbPlayer) {
                matchingGames.add(game);
            }
        }
        if (matchingGames.isEmpty()) {
            return Optional.empty();
        } else {
            Random random = new Random();
            return Optional.of(matchingGames.get(random.nextInt(matchingGames.size())));
        }
    }
}
