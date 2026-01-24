package fr.fges;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GameCollection {
    private final List<BoardGame> games = new ArrayList<>();
    private final GameRepository gameRepository;

    public GameCollection(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
        this.games.addAll(gameRepository.load());
    }

    public void addGame(BoardGame game) {
        games.add(game);
        gameRepository.save(games);
    }

    public void removeGame(BoardGame game) {
        games.remove(game);
        gameRepository.save(games);
    }

    public List<BoardGame> getGames() {
        return games;
    }

    public void viewAllGames() {
        if (games.isEmpty()) {
            System.out.println("No board games in collection.");
            return;
        }
        games.stream()
                .sorted(Comparator.comparing(BoardGame::title))
                .forEach(game -> System.out.println("Game: " + game.title() + " (" + game.minPlayers() + "-" + game.maxPlayers() + " players) - " + game.category()));
    }
}
