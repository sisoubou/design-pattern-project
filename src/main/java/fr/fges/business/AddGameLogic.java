package fr.fges.business;

import fr.fges.data.GameRepository;
import java.util.Objects;

public class AddGameLogic {
    private final GameRepository gameRepository;
    private final GameHistory history;

    public AddGameLogic(GameRepository gameRepository, GameHistory history) {
        this.gameRepository = gameRepository;
        this.history = history;
    }

    public void addGame(BoardGame game) {
        if (alreadyExist(game)) {
            throw new IllegalArgumentException(game.title());
        } else {
            gameRepository.add(game);
            history.push(() -> {
                gameRepository.remove(game);
                return "Removed " + game.title() + " from collection.";
            });
        }
    }

    public boolean alreadyExist(BoardGame newGame) {
        for (BoardGame game : gameRepository.getAll()) {
            if (Objects.equals(newGame.title(), game.title())) {
                return true;
            }
        }
        return false;
    }
}
