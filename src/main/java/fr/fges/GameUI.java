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

    public void showTournamentModeHeader() {
        System.out.println("\n=== Tournament Mode ===");
    }

    public void showAvailable2PlayerGames(List<BoardGame> games) {
        System.out.println("Available 2-player games:");
        for (int i = 0; i < games.size(); i++) {
            BoardGame game = games.get(i);
            System.out.printf("%d. %s (%d players, %s)%n", i + 1, game.title(), game.minPlayers(), game.category());
        }
    }

    public void showChooseTournamentFormat() {
        System.out.println("\nChoose format:");
        System.out.println("1. Championship (everyone plays everyone)");
        System.out.println("2. King of the Hill (winner stays)");
    }

    public void showMatchHeader(int currentMatch, int totalMatches) {
        System.out.println("\n=== Match " + currentMatch + "/" + totalMatches + " ===");
    }

    public void showMatchup(String player1, String player2) {
        System.out.println(player1 + " vs " + player2);
    }

    public void showWinnerPrompt(String player1, String player2) {
        System.out.print("Winner (1=" + player1 + ", 2=" + player2 + "): ");
    }

    public void showInvalidPlayerInput() {
        System.out.println("Invalid input. Please enter a number.");
    }

    public void showKingRemains(String playerName) {
        System.out.println(playerName + " remains the King of the Hill!");
    }

    public void showNewKing(String playerName) {
        System.out.println(playerName + " becomes the new King of the Hill!");
    }

    public void showTournamentResults(List<fr.fges.tournament.Player> players) {
        System.out.println("\n=== Tournament Results ===");
        java.util.List<fr.fges.tournament.Player> sorted = players.stream()
            .sorted((p1, p2) -> Integer.compare(p2.getPoints(), p1.getPoints()))
            .toList();
        for (int i = 0; i < sorted.size(); i++) {
            fr.fges.tournament.Player player = sorted.get(i);
            System.out.println((i + 1) + ". " + player);
        }
    }
}
