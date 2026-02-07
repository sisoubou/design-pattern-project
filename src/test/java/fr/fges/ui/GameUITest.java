package fr.fges.ui;


import fr.fges.BoardGame;
import fr.fges.GameUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameUITest {
    private GameUI ui;
    private PrintStream originalOut;
    private PrintStream originalErr;
    private ByteArrayOutputStream outContent;
    private ByteArrayOutputStream errContent;

    @BeforeEach
    void setUp() {
        ui = Mockito.spy(new GameUI());
        originalOut = System.out;
        originalErr = System.err;
        outContent = new ByteArrayOutputStream();
        errContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    void showErrorAlreadyExist_printsError() {
        ui.showErrorAlreadyExist("Catan");
        verify(ui).showErrorAlreadyExist("Catan");
        assertTrue(errContent.toString().contains("already exists"));
    }

    @Test
    void showSuccessAddGame_printsSuccess() {
        ui.showSuccessAddGame("Catan");
        verify(ui).showSuccessAddGame("Catan");
        assertTrue(outContent.toString().contains("added successfully"));
    }

    @Test
    void showErrorEmptyList_printsError() {
        ui.showErrorEmptyList();
        verify(ui).showErrorEmptyList();
        assertTrue(errContent.toString().contains("No board games"));
    }

    @Test
    void showGame_printsGame() {
        BoardGame game = new BoardGame("Catan", 3, 4, "Board");
        ui.showGame(game);
        verify(ui).showGame(game);
        assertTrue(outContent.toString().contains("Catan"));
    }

    @Test
    void showErrorNoMatch_printsError() {
        ui.showErrorNoMatch();
        verify(ui).showErrorNoMatch();
        assertTrue(errContent.toString().contains("no games matching"));
    }

    @Test
    void showRecommendedGame_printsRecommended() {
        BoardGame game = new BoardGame("Catan", 3, 4, "Board");
        ui.showRecommendedGame(game);
        verify(ui).showRecommendedGame(game);
        assertTrue(outContent.toString().contains("Recommended Game"));
    }

    @Test
    void showErrorNotEnoughGames_printsError() {
        ui.showErrorNotEnoughGames(2);
        verify(ui).showErrorNotEnoughGames(2);
        assertTrue(outContent.toString().contains("Not enough games"));
    }

    @Test
    void showList_printsGamesOrEmpty() {
        ui.showList(List.of());
        verify(ui).showList(List.of());
        assertTrue(outContent.toString().contains("No board game in collection"));
        outContent.reset();
        List<BoardGame> games = List.of(new BoardGame("Catan", 3, 4, "Board"));
        ui.showList(games);
        verify(ui, times(2)).showList(anyList());
        assertTrue(outContent.toString().contains("Catan"));
    }

    @Test
    void showSummary_printsSummary() {
        List<BoardGame> games = List.of(new BoardGame("Catan", 3, 4, "Board"));
        ui.showSummary(games);
        verify(ui).showSummary(games);
        assertTrue(outContent.toString().contains("== Summary"));
    }

    @Test
    void showError_printsCustomError() {
        ui.showError("custom error");
        verify(ui).showError("custom error");
        assertTrue(errContent.toString().contains("custom error"));
    }
}
