package fr.fges.command;

import fr.fges.BoardGame;
import fr.fges.GameCollection;
import fr.fges.GameUI;

import java.util.Scanner;

public class AddGameCommand extends InteractiveCommand {
    public final GameUI gameUI = new GameUI();

    public AddGameCommand(GameCollection gameCollection, Scanner scanner) {
        super(gameCollection, scanner);
    }

    @Override
    public void execute() {
        String title = getUserInput("Title");
        String minPlayersStr = getUserInput("Minimum Players");
        String maxPlayersStr = getUserInput("Maximum Players");
        String category = getUserInput("Category (e.g., fantasy, cooperative, family, strategy)");

        int minPlayers = Integer.parseInt(minPlayersStr);
        int maxPlayers = Integer.parseInt(maxPlayersStr);

        BoardGame game = new BoardGame(title, minPlayers, maxPlayers, category);

        try {
            gameCollection.addGame(game);
            gameUI.showSuccessAddGame(title);
        } catch (IllegalArgumentException e) {
            gameUI.showErrorAlreadyExist(title);
        }
    }

    @Override
    public String getDescription() {
        return "Add Board Game";
    }
}
