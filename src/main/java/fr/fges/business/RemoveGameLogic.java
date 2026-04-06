package fr.fges.business;

import fr.fges.data.GameRepository;
import java.util.List;

public class RemoveGameLogic {
    private final GameRepository gameRepository;
    private final GameHistory history;

    public RemoveGameLogic(GameRepository gameRepository, GameHistory history) {
        this.gameRepository = gameRepository;
        this.history = history;
    }

    public void removeGame(BoardGame game) {
        gameRepository.remove(game);
        history.push(() -> {
            gameRepository.add(game);
            return "Added " + game.title() + " back to the collection.";
        });
    }

    public List<BoardGame> getGames() {
        return gameRepository.getAll();
    }
}
