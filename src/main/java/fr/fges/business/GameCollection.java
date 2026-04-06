package fr.fges.business;

import fr.fges.data.GameRepository;

import java.util.*;

public class GameCollection {
    private final GameRepository gameRepository;
    private final Stack<UndoAction> history = new Stack<>();

    public GameCollection(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public void addGame(BoardGame game) {
        if (alreadyExist(game)){
            throw new IllegalArgumentException(game.title());
        }else {
            gameRepository.add(game);
            history.push(() -> {
                gameRepository.remove(game);
                return "Removed " + game.title() + " from collection.";
            });
        }
    }

    public void removeGame(BoardGame game) {
        gameRepository.remove(game);
        history.push(() -> {
            gameRepository.add(game);
            return "Added " + game.title() + " back to the collection.";
        });
    }

    public List<BoardGame> viewAllGames() {
        return gameRepository.getAll().stream()
                .sorted(Comparator.comparing(BoardGame::title))
                .toList();
    }

    public String undo() {
        if (history.isEmpty()) {
            throw new IllegalArgumentException("Nothing to undo.");
        }
        UndoAction lastAction = history.pop();
        return lastAction.undo();
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
