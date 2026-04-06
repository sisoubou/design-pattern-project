package fr.fges.ui;

import fr.fges.business.BoardGame;
import fr.fges.support.ConsoleCapture;
import fr.fges.tournament.Player;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GameUITest {
    private final GameUI gameUI = new GameUI();

    @Test
    void showListDisplaysEmptyMessageWhenThereIsNoGame() {
        try (ConsoleCapture capture = new ConsoleCapture()) {
            gameUI.showList(List.of());

            assertTrue(capture.stdout().contains("No board game in collection"));
        }
    }

    @Test
    void showListDisplaysEveryGame() {
        try (ConsoleCapture capture = new ConsoleCapture()) {
            gameUI.showList(List.of(
                    new BoardGame("Azul", 2, 4, "abstract"),
                    new BoardGame("Catan", 3, 4, "strategy")
            ));

            String stdout = capture.stdout();
            assertTrue(stdout.contains("Game: Azul (2-4 players) - abstract"));
            assertTrue(stdout.contains("Game: Catan (3-4 players) - strategy"));
        }
    }

    @Test
    void showErrorMethodsWriteToStandardError() {
        try (ConsoleCapture capture = new ConsoleCapture()) {
            gameUI.showErrorAlreadyExist("Azul");
            gameUI.showErrorNoMatch();
            gameUI.showError("Custom error");

            String stderr = capture.stderr();
            assertTrue(stderr.contains("Error: A game with title 'Azul' already exists"));
            assertTrue(stderr.contains("There are no games matching your request"));
            assertTrue(stderr.contains("Custom error"));
        }
    }

    @Test
    void showTournamentResultsSortsPlayersByPoints() throws Exception {
        Player alice = new Player("Alice");
        Player bob = new Player("Bob");
        invoke(playerMethod("addWin"), alice, 2);
        invoke(playerMethod("addWin"), bob, 1);

        try (ConsoleCapture capture = new ConsoleCapture()) {
            gameUI.showTournamentResults(List.of(bob, alice));

            String stdout = capture.stdout();
            assertTrue(stdout.indexOf("1. Alice - 6 points (2 wins)") < stdout.indexOf("2. Bob - 3 points (1 win)"));
        }
    }

    private Method playerMethod(String name) throws NoSuchMethodException {
        Method method = Player.class.getDeclaredMethod(name);
        method.setAccessible(true);
        return method;
    }

    private void invoke(Method method, Player player, int times) throws Exception {
        for (int i = 0; i < times; i++) {
            method.invoke(player);
        }
    }
}
