package fr.fges.command;

import fr.fges.BoardGame;
import fr.fges.GameCollection;
import fr.fges.GameResearch;
import fr.fges.GameUI;

import java.util.Scanner;

public class RemoveGameCommand extends InteractiveCommand {
    private final GameUI gameUI = new GameUI();
    private final CommandHistory history;

    protected RemoveGameCommand(GameCollection gameCollection, Scanner scanner, GameResearch gameResearch, CommandHistory history) {
        super(gameCollection, scanner, gameResearch);
        this.history = history;
    }


    @Override
    public void execute() {
        String title = getUserInput("Title of game to remove");
        var games = gameCollection.getGames();

        for (BoardGame game : games) {
            if (game.title().equals(title)) {
                gameCollection.removeGame(game);
                System.out.println("Board game removed successfully.");

                history.push(() -> {
                    try {
                        gameCollection.addGame(game);
                        return "Added \"" + game.title() + "\" back to the collection";
                    } catch (Exception e) {
                        return "Error restoring " + game.title();
                    }
                });

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
