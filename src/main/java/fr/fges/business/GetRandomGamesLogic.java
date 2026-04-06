package fr.fges.business;

import fr.fges.data.GameRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GetRandomGamesLogic {
    private final GameRepository gameRepository;

    public GetRandomGamesLogic(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<BoardGame> getRandomGames(int count) {
        if (gameRepository.getAll().isEmpty()) {
            return Collections.emptyList();
        }
        if (gameRepository.getAll().size() < count) {
            throw new IllegalArgumentException(String.valueOf(gameRepository.getAll().size()));
        }
        Random random = new Random();
        List<BoardGame> randomGames = new ArrayList<>();
        while (randomGames.size() < Math.min(count, gameRepository.getAll().size())) {
            BoardGame randomGame = gameRepository.getAll().get(random.nextInt(gameRepository.getAll().size()));
            randomGames.add(randomGame);
        }
        return randomGames;
    }
}
