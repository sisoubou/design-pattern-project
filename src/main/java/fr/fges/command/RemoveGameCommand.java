package fr.fges.command;

import fr.fges.business.BoardGame;
import fr.fges.business.RemoveGameLogic;
import fr.fges.ui.UserInput;

public class RemoveGameCommand implements Command {
    private final RemoveGameLogic removeGameLogic;
    private final UserInput userInput;

    public RemoveGameCommand(RemoveGameLogic removeGameLogic, UserInput userInput) {
        this.removeGameLogic = removeGameLogic;
        this.userInput = userInput;
    }

    @Override
    public void execute() {
        String title = userInput.getUserInput("Title of game to remove");
        var games = removeGameLogic.getGames();

        for (BoardGame game : games) {
            if (game.title().equals(title)) {
                removeGameLogic.removeGame(game);
                System.out.println("Board game removed successfully.");

                return;
            }
        }
        System.out.println("No board game found with that title.");

    }

    @Override
    public String getDescription() {
        return "Remove Board Game";
    }
}
