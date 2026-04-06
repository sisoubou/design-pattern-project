package fr.fges.tournament;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {
    @Test
    void addWinUpdatesPointsWinsAndSingularLabel() {
        Player player = new Player("Alice");

        player.addWin();

        assertEquals(3, player.getPoints());
        assertEquals(1, player.getWins());
        assertEquals("Alice - 3 points (1 win)", player.toString());
    }

    @Test
    void addLossAndMultipleWinsUsePluralLabel() {
        Player player = new Player("Bob");

        player.addLoss();
        player.addWin();
        player.addWin();

        assertEquals(7, player.getPoints());
        assertEquals(2, player.getWins());
        assertEquals("Bob - 7 points (2 wins)", player.toString());
    }
}
