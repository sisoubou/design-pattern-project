package fr.fges.tournament;

import fr.fges.support.ConsoleCapture;
import fr.fges.ui.UserInput;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MatchTest {
    @Test
    void playerOneWinsAndScoresAreUpdated() {
        Player alice = new Player("Alice");
        Player bob = new Player("Bob");
        Match match = new Match(alice, bob, new UserInput(new Scanner("1\n")));

        Player winner = match.playMatch();

        assertEquals(alice, winner);
        assertEquals(3, alice.getPoints());
        assertEquals(1, bob.getPoints());
    }

    @Test
    void invalidInputsAreRetriedUntilAValidWinnerIsChosen() {
        Player alice = new Player("Alice");
        Player bob = new Player("Bob");
        Match match = new Match(alice, bob, new UserInput(new Scanner("x\n3\n2\n")));

        try (ConsoleCapture capture = new ConsoleCapture()) {
            Player winner = match.playMatch();

            assertEquals(bob, winner);
            assertEquals(1, alice.getPoints());
            assertEquals(3, bob.getPoints());
            assertTrue(capture.stderr().contains("Invalid input. Please enter 1 or 2."));
        }
    }
}
