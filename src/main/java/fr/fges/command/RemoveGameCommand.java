package fr.fges.command;

import fr.fges.BoardGame;
import fr.fges.GameCollection;

public class RemoveGameCommand implements Command {
    private final GameCollection gameCollection;
    private final UserInput userInput;

    public RemoveGameCommand(GameCollection gameCollection, UserInput userInput) {
        this.gameCollection = gameCollection;
        this.userInput = userInput;
    }

    @Override
    public void execute() {
        String title = userInput.getUserInput("Title of game to remove");
        var games = gameCollection.getGames();

        for (BoardGame game : games) {
            if (game.title().equals(title)) {
                gameCollection.removeGame(game);
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
