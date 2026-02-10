package fr.fges;

import fr.fges.data.GameRepository;

import java.util.*;

public class GameResearch {
    private final GameRepository gameRepository;

    public GameResearch(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Optional<BoardGame> recommendGame(int nbPlayer) {
        List<BoardGame> matchingGames = new ArrayList<>();
        for (BoardGame game: gameRepository.getAll()){
            if (game.minPlayers() <= nbPlayer && game.maxPlayers() >= nbPlayer) {
                matchingGames.add(game);
            }
        }
        if (matchingGames.isEmpty()){
            return Optional.empty();
        }else{
            Random random = new Random();
            return Optional.of(matchingGames.get(random.nextInt(matchingGames.size())));
        }
    }

    public List<BoardGame> getRandomGames (int count){
        if (gameRepository.getAll().isEmpty()) {
            return Collections.emptyList();
        }
        if (gameRepository.getAll().size()<count){
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

    public List<BoardGame> getAllGamesNumberMatch(int nbPlayer){
        if (gameRepository.getAll().isEmpty()) {
            return Collections.emptyList();
        }
        List<BoardGame> allGamesMatch = new ArrayList<>();
        GameCollection gameCollection = new GameCollection(gameRepository);
        List<BoardGame> gamesSorted = gameCollection.viewAllGames();
        for (BoardGame game: gamesSorted){
            if (game.minPlayers() <= nbPlayer && game.maxPlayers() >= nbPlayer) {
                allGamesMatch.add(game);
            }
        }
        if (allGamesMatch.isEmpty()){
            throw new IllegalArgumentException();
        }else{
            return allGamesMatch;
        }
    }

}
