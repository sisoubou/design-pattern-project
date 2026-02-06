package fr.fges;

import fr.fges.data.GameRepository;

import java.util.*;

public class GameCollection {
    private final List<BoardGame> games = new ArrayList<>();
    private final GameRepository gameRepository;

    public GameCollection(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
        this.games.addAll(gameRepository.load());
    }

    public void addGame(BoardGame game) {
        if (alreadyExist(game)){
            throw new IllegalArgumentException(game.title());
        }else {
            games.add(game);
            gameRepository.save(games);
        }
    }

    public void removeGame(BoardGame game) {
        games.remove(game);
        gameRepository.save(games);
    }

    public List<BoardGame> viewAllGames() {
        return games.stream()
                .sorted(Comparator.comparing(BoardGame::title))
                .toList();
    }

    public boolean alreadyExist (BoardGame newGame){
        for (BoardGame game: games){
            if (Objects.equals(newGame.title(), game.title())) {
                return true;
            }
        }
        return false;
    }

    public Optional<BoardGame> recommendGame(int nbPlayer) {
        List<BoardGame> matchingGames = new ArrayList<>();
        for (BoardGame game: games){
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
        if (games.isEmpty()) {
            return Collections.emptyList();
        }
        if (games.size()<count){
            throw new IllegalArgumentException(String.valueOf(games.size()));
        }
        Random random = new Random();
        List<BoardGame> randomGames = new ArrayList<>();
        while (randomGames.size() < Math.min(count, games.size())) {
            BoardGame randomGame = games.get(random.nextInt(games.size()));
            randomGames.add(randomGame);
        }
        return randomGames;

    }
}
