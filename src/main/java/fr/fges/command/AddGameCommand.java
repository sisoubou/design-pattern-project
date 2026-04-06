package fr.fges.command;

import fr.fges.BoardGame;
import fr.fges.GameCollection;
import fr.fges.GameUI;

public class AddGameCommand implements Command {
    private final GameCollection gameCollection;
    private final UserInput userInput;
    private final GameUI gameUI = new GameUI();
    private final CommandHistory history;

    public AddGameCommand(GameCollection gameCollection, UserInput userInput, CommandHistory history) {
        this.gameCollection = gameCollection;
        this.userInput = userInput;
        this.history = history;
    }


    @Override
    public void execute() {
        String title = userInput.getUserInput("Title");
        String minPlayersStr = userInput.getUserInput("Minimum Players");
        String maxPlayersStr = userInput.getUserInput("Maximum Players");
        String category = userInput.getUserInput("Category (e.g., fantasy, cooperative, family, strategy)");

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
