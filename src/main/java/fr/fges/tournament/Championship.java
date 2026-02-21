package fr.fges.tournament;

import fr.fges.GameUI;
import java.util.List;
import java.util.Scanner;

public class Championship implements Tournament {
    private final GameUI gameUI;

    public Championship(GameUI gameUI) {
        this.gameUI = gameUI;
    }

    @Override
    public void playTournament(List<Player> players, Scanner scanner) {
        if (players.isEmpty() || players.size() < 2) {
            gameUI.showError("Not enough players for a tournament.");
            return;
        }

        // Calculate total number of matches
        int totalMatches = (players.size() * (players.size() - 1)) / 2;
        int currentMatch = 0;

        for (int i = 0; i < players.size(); i++) {
            for (int j = i + 1; j < players.size(); j++) {
                currentMatch++;

                Player player1 = players.get(i);
                Player player2 = players.get(j);

                gameUI.showMatchHeader(currentMatch, totalMatches);
                gameUI.showMatchup(player1.getName(), player2.getName());

                Match match = new Match(player1, player2, scanner, gameUI);
                match.playMatch();
            }
        }
    }
}
