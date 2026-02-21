package fr.fges.tournament;

import fr.fges.GameUI;
import java.util.List;
import java.util.Scanner;

public class KingOfTheHill implements Tournament {
    private final GameUI gameUI;

    public KingOfTheHill(GameUI gameUI) {
        this.gameUI = gameUI;
    }

    @Override
    public void playTournament(List<Player> players, Scanner scanner) {
        if (players.isEmpty() || players.size() < 2) {
            gameUI.showError("Not enough players for a tournament.");
            return;
        }

        Player currentWinner = players.get(0);
        int matchCount = 0;
        int totalMatches = players.size() - 1;

        for (int i = 1; i < players.size(); i++) {
            matchCount++;
            Player challenger = players.get(i);
            
            gameUI.showMatchHeader(matchCount, totalMatches);
            gameUI.showMatchup(currentWinner.getName(), challenger.getName());
            
            Match match = new Match(currentWinner, challenger, scanner, gameUI);
            Player matchWinner = match.playMatch();

            if (currentWinner == matchWinner) {
                gameUI.showKingRemains(currentWinner.getName());
            } else {
                currentWinner = challenger;
                gameUI.showNewKing(currentWinner.getName());
            }
        }
    }
}
