package fr.fges.tournament;

import java.util.List;
import java.util.Scanner;

public class KingOfTheHill implements Tournament{
    @Override
    public void playTournament(List<Player> players, Scanner scanner) {
        if (players.isEmpty() || players.size() < 2) {
            System.out.println("Not enough players for a tournament.");
            return;
        }

        Player currentWinner = players.get(0);

        for (int i = 1; i < players.size(); i++) {
            Player challenger = players.get(i);
            Match match = new Match(currentWinner, challenger, scanner);
            Player matchWinner = match.playMatch();

            if (currentWinner == matchWinner) {
                System.out.println(currentWinner.getName() + " remains the King of the Hill!");
            } else {
                currentWinner = challenger;
                System.out.println(currentWinner.getName() + " becomes the new King of the Hill!");
            }
        }
    }
}
