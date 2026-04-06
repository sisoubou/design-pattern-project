package fr.fges.command;

import fr.fges.business.BoardGame;
import fr.fges.business.GetAllGamesNumberMatchLogic;
import fr.fges.ui.TournamentView;
import fr.fges.ui.TournamentUI;
import fr.fges.tournament.Championship;
import fr.fges.tournament.KingOfTheHill;
import fr.fges.tournament.Player;
import fr.fges.tournament.Tournament;
import fr.fges.ui.UserInput;

import java.util.ArrayList;
import java.util.List;

public class TournamentCommand implements Command {
    private final GetAllGamesNumberMatchLogic getAllGamesNumberMatchLogic;
    private final UserInput userInput;
    private final TournamentView tournamentView = new TournamentView();

    public TournamentCommand(GetAllGamesNumberMatchLogic getAllGamesNumberMatchLogic, UserInput userInput) {
        this.getAllGamesNumberMatchLogic = getAllGamesNumberMatchLogic;
        this.userInput = userInput;
    }

    @Override
    public void execute() {
        try {
            tournamentView.showTournamentModeHeader();

            List<BoardGame> availableGames;
            try {
                availableGames = getAllGamesNumberMatchLogic.getAllGamesNumberMatch(2);
            } catch (IllegalArgumentException e) {
                tournamentView.showError("No games available for 2 players. Cannot organize a tournament.");
                return;
            }

            tournamentView.showAvailable2PlayerGames(availableGames);

            String gameChoice = userInput.getUserInput("Select game (1-" + availableGames.size() + ")");
            int gameIndex;
            try {
                gameIndex = Integer.parseInt(gameChoice) - 1;
                if (gameIndex < 0 || gameIndex >= availableGames.size()) {
                    tournamentView.showError("Invalid game selection.");
                    return;
                }
            } catch (NumberFormatException e) {
                tournamentView.showError("Invalid input. Please enter a number.");
                return;
            }

            String nbParticipantsStr = userInput.getUserInput("Number of participants (3-8)");
            int nbParticipants;
            try {
                nbParticipants = Integer.parseInt(nbParticipantsStr);
                if (nbParticipants < 3 || nbParticipants > 8) {
                    tournamentView.showError("Number of participants must be between 3 and 8.");
                    return;
                }
            } catch (NumberFormatException e) {
                tournamentView.showError("Invalid input. Please enter a number.");
                return;
            }

            List<Player> players = new ArrayList<>();
            for (int i = 0; i < nbParticipants; i++) {
                String playerName = userInput.getUserInput("Enter player " + (i + 1) + " name");
                players.add(new Player(playerName));
            }

            tournamentView.showChooseTournamentFormat();
            String formatChoice = userInput.getUserInput("Select format (1-2)");

            TournamentUI tournamentUI = new TournamentUI() {
                @Override
                public void onMatchStart(int currentMatch, int totalMatches, String player1, String player2) {
                    tournamentView.showMatchHeader(currentMatch, totalMatches);
                    tournamentView.showMatchup(player1, player2);
                }

                @Override
                public void onKingRemains(String playerName) {
                    tournamentView.showKingRemains(playerName);
                }

                @Override
                public void onNewKing(String playerName) {
                    tournamentView.showNewKing(playerName);
                }
            };

            Tournament tournament;
            if ("1".equals(formatChoice)) {
                tournament = new Championship();
            } else if ("2".equals(formatChoice)) {
                tournament = new KingOfTheHill();
            } else {
                tournamentView.showError("Invalid format. Using Championship by default.");
                tournament = new Championship();
            }

            tournament.playTournament(players, userInput, tournamentUI);

            tournamentView.showTournamentResults(players);

        } catch (Exception e) {
            tournamentView.showError("An error occurred: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Tournament Mode";
    }
}
