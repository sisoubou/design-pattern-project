package fr.fges.tournament;

import fr.fges.ui.TournamentUI;
import fr.fges.ui.UserInput;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class KingOfTheHillTest {
    @Test
    void throwsWhenThereAreNotEnoughPlayers() {
        KingOfTheHill tournament = new KingOfTheHill();
        List<Player> players = List.of(new Player("Alice"));

        assertThrows(
                IllegalArgumentException.class,
                () -> tournament.playTournament(players, new UserInput(new Scanner("")), new RecordingTournamentUI())
        );
    }

    @Test
    void announcesNewKingAndKingRemainsEvents() {
        KingOfTheHill tournament = new KingOfTheHill();
        List<Player> players = new ArrayList<>(List.of(
                new Player("Alice"),
                new Player("Bob"),
                new Player("Chloe")
        ));
        RecordingTournamentUI ui = new RecordingTournamentUI();

        tournament.playTournament(players, new UserInput(new Scanner("2\n1\n")), ui);

        assertEquals(List.of("1/2:Alice:Bob", "2/2:Bob:Chloe"), ui.matchStarts);
        assertEquals(List.of("Bob"), ui.newKings);
        assertEquals(List.of("Bob"), ui.kingsRemaining);
        assertEquals(6, players.get(1).getPoints());
    }

    private static class RecordingTournamentUI implements TournamentUI {
        private final List<String> matchStarts = new ArrayList<>();
        private final List<String> kingsRemaining = new ArrayList<>();
        private final List<String> newKings = new ArrayList<>();

        @Override
        public void onMatchStart(int currentMatch, int totalMatches, String player1, String player2) {
            matchStarts.add(currentMatch + "/" + totalMatches + ":" + player1 + ":" + player2);
        }

        @Override
        public void onKingRemains(String playerName) {
            kingsRemaining.add(playerName);
        }

        @Override
        public void onNewKing(String playerName) {
            newKings.add(playerName);
        }
    }
}
