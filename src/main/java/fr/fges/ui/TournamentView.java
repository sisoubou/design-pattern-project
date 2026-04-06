package fr.fges.ui;

import fr.fges.business.BoardGame;
import fr.fges.tournament.Player;

import java.util.List;

public class TournamentView {

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

    public void showKingRemains(String playerName) {
        System.out.println(playerName + " remains the King of the Hill!");
    }

    public void showNewKing(String playerName) {
        System.out.println(playerName + " becomes the new King of the Hill!");
    }

    public void showTournamentResults(List<Player> players) {
        System.out.println("\n=== Tournament Results ===");
        List<Player> sorted = players.stream()
                .sorted((p1, p2) -> Integer.compare(p2.getPoints(), p1.getPoints()))
                .toList();
        for (int i = 0; i < sorted.size(); i++) {
            Player player = sorted.get(i);
            System.out.println((i + 1) + ". " + player);
        }
    }

    public void showError(String message) {
        System.err.println(message);
    }
}
