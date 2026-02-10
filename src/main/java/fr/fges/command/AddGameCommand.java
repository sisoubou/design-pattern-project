package fr.fges.command;

import fr.fges.BoardGame;
import fr.fges.GameCollection;
import fr.fges.GameResearch;
import fr.fges.GameUI;

import java.util.Scanner;

public class AddGameCommand extends InteractiveCommand {
    private final GameUI gameUI = new GameUI();
    private final CommandHistory history;

    protected AddGameCommand(GameCollection gameCollection, Scanner scanner, GameResearch gameResearch, CommandHistory history) {
        super(gameCollection, scanner, gameResearch);
        this.history = history;
    }


    @Override
    public void execute() {
        String title = getUserInput("Title");
        String minPlayersStr = getUserInput("Minimum Players");
        String maxPlayersStr = getUserInput("Maximum Players");
        String category = getUserInput("Category (e.g., fantasy, cooperative, family, strategy)");

        try {
            int minPlayers = Integer.parseInt(minPlayersStr);
            int maxPlayers = Integer.parseInt(maxPlayersStr);
            BoardGame game = new BoardGame(title, minPlayers, maxPlayers, category);

            gameCollection.addGame(game);
            gameUI.showSuccessAddGame(title);

            history.push(() -> {
                gameCollection.removeGame(game);
                return "Removed \"" + game.title() + "\" from collection.";
            });
        } catch (IllegalArgumentException e) {
            gameUI.showErrorAlreadyExist(title);
        }
    }

    @Override
    public String getDescription() {
        return "Add Board Game";
    }
}
