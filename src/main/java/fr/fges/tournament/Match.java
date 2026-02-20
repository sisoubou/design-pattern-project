package fr.fges.tournament;

import java.util.Scanner;

public class Match {
    private final Player player1;
    private final Player player2;
    private final Scanner scanner;

    public Match(Player player1, Player player2, Scanner scanner) {
        this.player1 = player1;
        this.player2 = player2;
        this.scanner = scanner;
    }

    public Player playMatch() {
        System.out.println(player1.getName() + " vs " + player2.getName());

        int winner = -1;
        while (winner != 1 && winner != 2) {
            System.out.println("Winner (1=" + player1.getName() + "2=" + player2.getName() + "): ");
            String input = scanner.nextLine();

            try {
                winner = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
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
