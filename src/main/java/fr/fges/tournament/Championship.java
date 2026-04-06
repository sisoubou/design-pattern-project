package fr.fges.tournament;

import fr.fges.ui.UserInput;
import fr.fges.ui.TournamentUI;

import java.util.List;

public class Championship implements Tournament {

    @Override
    public void playTournament(List<Player> players, UserInput userInput, TournamentUI ui) {
        if (players.size() < 2) {
            throw new IllegalArgumentException("Not enough players for a tournament.");
        }

        int totalMatches = (players.size() * (players.size() - 1)) / 2;
        int currentMatch = 0;

        for (int i = 0; i < players.size(); i++) {
            for (int j = i + 1; j < players.size(); j++) {
                currentMatch++;

                Player player1 = players.get(i);
                Player player2 = players.get(j);

                ui.onMatchStart(currentMatch, totalMatches, player1.getName(), player2.getName());

                Match match = new Match(player1, player2, userInput);
                match.playMatch();
            }
        }
    }
}
