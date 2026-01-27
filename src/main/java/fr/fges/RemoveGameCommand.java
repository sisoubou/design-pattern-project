package fr.fges;

import java.util.Scanner;

public class RemoveGameCommand extends InteractiveCommand{

    public RemoveGameCommand(GameCollection gameCollection, Scanner scanner) {
        super(gameCollection, scanner);
    }

    @Override
    public void execute() {
        String title = getUserInput("Title of game to remove");

        // get games from the collection, find the one that matches the title given by the user and remove
        var games = gameCollection.getGames();

        for (BoardGame game : games) {
            if (game.title().equals(title)) {
                GameCollection.removeGame(game);
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
