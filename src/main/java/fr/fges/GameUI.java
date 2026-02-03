package fr.fges;

import java.util.List;

public class GameUI {
    public void showErrorAlreadyExist(String title){
        System.err.println("Error: A game with title '" + title + "' already exists in the collection.");
    }

    public void showSuccessAddGame(String title) {
        System.out.println(title + " has been added successfully");
    }

    public void showErrorEmptyList() {
        System.err.println("No board games in collection.");
    }

    public void showGame(BoardGame game){
        System.out.println("Game: " + game.title() + " (" + game.minPlayers() + "-" + game.maxPlayers() + " players) - " + game.category());
    }

    public void showErrorNoMatch() {
        System.err.println("There are no games matching your request");
    }

    public void showRecommendedGame(BoardGame recommended) {
        System.out.println("Recommended Game: " + recommended.title() + " (" + recommended.minPlayers() + "-" + recommended.maxPlayers() + " players) - " + recommended.category());
    }

    public void showErrorNotEnoughGames(int count) {
        System.out.println("Not enough games in collection to get " + count + " random games.");
    }

    public void showList(List<BoardGame> games){
        if (games.isEmpty()){
            System.out.println("No board game in collection");
            return;
        }
        games.forEach(this::showGame);
    }

    public void showSummary(List<BoardGame> games){
        System.out.println("== Summary (" + games.size() + " random games) ==");
        games.forEach(this::showGame);
    }

    public void showError(String message){
        System.err.println(message);
    }
}
