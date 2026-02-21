package fr.fges.tournament;

import fr.fges.GameUI;
import java.util.Scanner;

public class Match {
    private final Player player1;
    private final Player player2;
    private final Scanner scanner;
    private final GameUI gameUI;

    public Match(Player player1, Player player2, Scanner scanner, GameUI gameUI) {
        this.player1 = player1;
        this.player2 = player2;
        this.scanner = scanner;
        this.gameUI = gameUI;
    }

    public Player playMatch() {
        int winner = -1;
        while (winner != 1 && winner != 2) {
            gameUI.showWinnerPrompt(player1.getName(), player2.getName());
            String input = scanner.nextLine();

            try {
                winner = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                gameUI.showInvalidPlayerInput();
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
