package fr.fges.command;

import fr.fges.business.BoardGame;
import fr.fges.business.AddGameLogic;
import fr.fges.ui.GameUI;
import fr.fges.ui.UserInput;

public class AddGameCommand implements Command {
    private final AddGameLogic addGameLogic;
    private final UserInput userInput;
    private final GameUI gameUI = new GameUI();

    public AddGameCommand(AddGameLogic addGameLogic, UserInput userInput) {
        this.addGameLogic = addGameLogic;
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

            addGameLogic.addGame(game);
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
