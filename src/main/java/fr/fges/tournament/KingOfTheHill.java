package fr.fges.tournament;

import fr.fges.ui.UserInput;
import fr.fges.ui.TournamentUI;

import java.util.List;

public class KingOfTheHill implements Tournament {


    @Override
    public void playTournament(List<Player> players, UserInput userInput, TournamentUI ui) {
        if (players.size() < 2) {
            throw new IllegalArgumentException("Not enough players for a tournament.");
        }

        Player currentWinner = players.get(0);
        int totalMatches = players.size() - 1;

        for (int i = 1; i < players.size(); i++) {
            Player challenger = players.get(i);

            ui.onMatchStart(i, totalMatches, currentWinner.getName(), challenger.getName());

            Match match = new Match(currentWinner, challenger, userInput);
            Player matchWinner = match.playMatch();

            if (currentWinner == matchWinner) {
                ui.onKingRemains(currentWinner.getName());
            } else {
                currentWinner = challenger;
                ui.onNewKing(currentWinner.getName());
            }
        }
    }
}