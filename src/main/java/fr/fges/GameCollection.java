package fr.fges;

import fr.fges.data.GameRepository;

import java.util.*;

public class GameCollection {
    private final GameRepository gameRepository;

    public GameCollection(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public void addGame(BoardGame game) {
        if (alreadyExist(game)){
            throw new IllegalArgumentException(game.title());
        }else {
            gameRepository.add(game);
        }
    }

    public void removeGame(BoardGame game) {
        gameRepository.remove(game);
    }

    public List<BoardGame> viewAllGames() {
        return gameRepository.getAll().stream()
                .sorted(Comparator.comparing(BoardGame::title))
                .toList();
    }

    public boolean alreadyExist (BoardGame newGame){
        for (BoardGame game: gameRepository.getAll()){
            if (Objects.equals(newGame.title(), game.title())) {
                return true;
            }
        }
        return false;
    }

    public List<BoardGame> getGames() {
        return gameRepository.getAll();
    }
}
