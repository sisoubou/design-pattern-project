package fr.fges.command;

import fr.fges.BoardGame;
import fr.fges.GameCollection;
import fr.fges.ui.GameUI;

public class AddGameCommand implements Command {
    private final GameCollection gameCollection;
    private final UserInput userInput;
    private final GameUI gameUI = new GameUI();

    public AddGameCommand(GameCollection gameCollection, UserInput userInput) {
        this.gameCollection = gameCollection;
        this.userInput = userInput;
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

        } catch (IllegalArgumentException e) {
            gameUI.showErrorAlreadyExist(title);
        }
    }

    @Override
    public String getDescription() {
        return "Add Board Game";
    }
}
