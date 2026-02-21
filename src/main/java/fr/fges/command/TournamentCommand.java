package fr.fges.command;

import fr.fges.BoardGame;
import fr.fges.GameCollection;
import fr.fges.GameResearch;
import fr.fges.GameUI;
import fr.fges.tournament.Championship;
import fr.fges.tournament.KingOfTheHill;
import fr.fges.tournament.Player;
import fr.fges.tournament.Tournament;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TournamentCommand extends InteractiveCommand {
    private final GameUI gameUI = new GameUI();

    public TournamentCommand(GameCollection gameCollection, Scanner scanner, GameResearch gameResearch) {
        super(gameCollection, scanner, gameResearch);
    }

    @Override
    public void execute() {
        try {
            gameUI.showTournamentModeHeader();

            // Display available 2-player games
            List<BoardGame> availableGames;
            try {
                availableGames = gameResearch.getAllGamesNumberMatch(2);
            } catch (IllegalArgumentException e) {
                gameUI.showError("No games available for 2 players. Cannot organize a tournament.");
                return;
            }

            gameUI.showAvailable2PlayerGames(availableGames);

            // Select game
            String gameChoice = getUserInput("Select game (1-" + availableGames.size() + ")");
            int gameIndex;
            try {
                gameIndex = Integer.parseInt(gameChoice) - 1;
                if (gameIndex < 0 || gameIndex >= availableGames.size()) {
                    gameUI.showError("Invalid game selection.");
                    return;
                }
            } catch (NumberFormatException e) {
                gameUI.showError("Invalid input. Please enter a number.");
                return;
            }

            // Get number of participants (3-8)
            String nbParticipantsStr = getUserInput("Number of participants (3-8)");
            int nbParticipants;
            try {
                nbParticipants = Integer.parseInt(nbParticipantsStr);
                if (nbParticipants < 3 || nbParticipants > 8) {
                    gameUI.showError("Number of participants must be between 3 and 8.");
                    return;
                }
            } catch (NumberFormatException e) {
                gameUI.showError("Invalid input. Please enter a number.");
                return;
            }

            // Create players
            List<Player> players = new ArrayList<>();
            for (int i = 0; i < nbParticipants; i++) {
                String playerName = getUserInput("Enter player " + (i + 1) + " name");
                players.add(new Player(playerName));
            }

            // Choose tournament format
            gameUI.showChooseTournamentFormat();
            String formatChoice = getUserInput("Select format (1-2)");

            Tournament tournament;
            if ("1".equals(formatChoice)) {
                tournament = new Championship(gameUI);
            } else if ("2".equals(formatChoice)) {
                tournament = new KingOfTheHill(gameUI);
            } else {
                gameUI.showError("Invalid format. Using Championship by default.");
                tournament = new Championship(gameUI);
            }

            // Play tournament
            tournament.playTournament(players, scanner);

            // Display results
            gameUI.showTournamentResults(players);

        } catch (Exception e) {
            gameUI.showError("An error occurred: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Tournament Mode";
    }
}
