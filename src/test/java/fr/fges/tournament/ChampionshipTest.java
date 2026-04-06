package fr.fges.tournament;

import fr.fges.ui.TournamentUI;
import fr.fges.ui.UserInput;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChampionshipTest {
    @Test
    void throwsWhenThereAreNotEnoughPlayers() {
        Championship tournament = new Championship();
        List<Player> players = List.of(new Player("Alice"));

        assertThrows(
                IllegalArgumentException.class,
                () -> tournament.playTournament(players, new UserInput(new Scanner("")), new RecordingTournamentUI())
        );
    }

    @Test
    void playsEveryRoundRobinMatchAndReportsThemToTheUi() {
        Championship tournament = new Championship();
        List<Player> players = new ArrayList<>(List.of(
                new Player("Alice"),
                new Player("Bob"),
                new Player("Chloe")
        ));
        RecordingTournamentUI ui = new RecordingTournamentUI();

        tournament.playTournament(players, new UserInput(new Scanner("1\n1\n1\n")), ui);

        assertEquals(List.of("1/3:Alice:Bob", "2/3:Alice:Chloe", "3/3:Bob:Chloe"), ui.matchStarts);
        assertEquals(6, players.get(0).getPoints());
        assertEquals(1, players.get(1).getWins());
        assertEquals(2, players.get(2).getPoints());
    }

    private static class RecordingTournamentUI implements TournamentUI {
        private final List<String> matchStarts = new ArrayList<>();

        @Override
        public void onMatchStart(int currentMatch, int totalMatches, String player1, String player2) {
            matchStarts.add(currentMatch + "/" + totalMatches + ":" + player1 + ":" + player2);
        }

        @Override
        public void onKingRemains(String playerName) {
        }

        @Override
        public void onNewKing(String playerName) {
        }
    }
}
