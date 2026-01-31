package fr.fges;

import java.util.*;

public class GameCollection {
    private final List<BoardGame> games = new ArrayList<>(); //pas besoin
    private final GameRepository gameRepository;

    public GameCollection(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
        this.games.addAll(gameRepository.load());
    }

    public void addGame(BoardGame game) {
        if (alreadyExist(game)){
            System.out.println("Error: A game with title '" + game.title() + "' already exists in the collection.");
        }else {
            games.add(game);
            gameRepository.save(games);
            System.out.println(game.title() + " has been added successfully");
        }
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

    public boolean alreadyExist (BoardGame newGame){
        for (BoardGame game: games){
            if (Objects.equals(newGame.title(), game.title())) {
                return true;
            }
        }
        return false;
    }

    public void recommendGame(int nbPlayer) {
        List<BoardGame> matchingGames = new ArrayList<>();
        for (BoardGame game: games){
            if (game.minPlayers() <= nbPlayer && game.maxPlayers() >= nbPlayer) {
                matchingGames.add(game);
            }
        }
        if (matchingGames.isEmpty()){
            System.out.println("There are no games matching your request");
        }else{
            Random random = new Random();
            BoardGame recommended = matchingGames.get(random.nextInt(matchingGames.size()));
            System.out.println("Recommended Game: " + recommended.title() + " (" + recommended.minPlayers() + "-" + recommended.maxPlayers() + " players) - " + recommended.category());        }
    }
}
