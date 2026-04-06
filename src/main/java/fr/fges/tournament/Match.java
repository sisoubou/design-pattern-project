package fr.fges.tournament;

import fr.fges.command.UserInput;

public class Match {
    private final Player player1;
    private final Player player2;
    private final UserInput userInput;

    public Match(Player player1, Player player2, UserInput userInput) {
        this.player1 = player1;
        this.player2 = player2;
        this.userInput = userInput;
    }

    public Player playMatch() {
        int winner = -1;
        while (winner != 1 && winner != 2) {
            String input = userInput.getUserInput("Winner (1=" + player1.getName() + ", 2=" + player2.getName() + ")");
            try {
                winner = Integer.parseInt(input);
                if (winner != 1 && winner != 2) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid input. Please enter 1 or 2.");
            }
        }

        if (winner == 1) {
            player1.addWin();
            player2.addLoss();
            return player1;
        } else {
            player2.addWin();
            player1.addLoss();
            return player2;
        }
    }
}
