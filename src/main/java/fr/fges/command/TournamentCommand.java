package fr.fges.command;

import fr.fges.BoardGame;
import fr.fges.GameResearch;
import fr.fges.ui.GameUI;
import fr.fges.ui.TournamentUI;
import fr.fges.tournament.Championship;
import fr.fges.tournament.KingOfTheHill;
import fr.fges.tournament.Player;
import fr.fges.tournament.Tournament;

import java.util.ArrayList;
import java.util.List;

public class TournamentCommand implements Command {
    private final GameResearch gameResearch;
    private final UserInput userInput;
    private final GameUI gameUI = new GameUI();

    public TournamentCommand(GameResearch gameResearch, UserInput userInput) {
        this.gameResearch = gameResearch;
        this.userInput = userInput;
    }

    @Override
    public void execute() {
        try {
            gameUI.showTournamentModeHeader();

            List<BoardGame> availableGames;
            try {
                availableGames = gameResearch.getAllGamesNumberMatch(2);
            } catch (IllegalArgumentException e) {
                gameUI.showError("No games available for 2 players. Cannot organize a tournament.");
                return;
            }

            gameUI.showAvailable2PlayerGames(availableGames);

            String gameChoice = userInput.getUserInput("Select game (1-" + availableGames.size() + ")");
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

            String nbParticipantsStr = userInput.getUserInput("Number of participants (3-8)");
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

            List<Player> players = new ArrayList<>();
            for (int i = 0; i < nbParticipants; i++) {
                String playerName = userInput.getUserInput("Enter player " + (i + 1) + " name");
                players.add(new Player(playerName));
            }

            gameUI.showChooseTournamentFormat();
            String formatChoice = userInput.getUserInput("Select format (1-2)");

            TournamentUI tournamentUI = new TournamentUI() {
                @Override
                public void onMatchStart(int currentMatch, int totalMatches, String player1, String player2) {
                    gameUI.showMatchHeader(currentMatch, totalMatches);
                    gameUI.showMatchup(player1, player2);
                }

                @Override
                public void onKingRemains(String playerName) {
                    gameUI.showKingRemains(playerName);
                }

                @Override
                public void onNewKing(String playerName) {
                    gameUI.showNewKing(playerName);
                }
            };

            Tournament tournament;
            if ("1".equals(formatChoice)) {
                tournament = new Championship();
            } else if ("2".equals(formatChoice)) {
                tournament = new KingOfTheHill();
            } else {
                gameUI.showError("Invalid format. Using Championship by default.");
                tournament = new Championship();
            }

            tournament.playTournament(players, userInput, tournamentUI);

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
