package fr.fges;

import java.util.Scanner;

public class AddGameCommand extends InteractiveCommand {

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

        gameCollection.addGame(game);
    }

    @Override
    public String getDescription() {
        return "Add Board Game";
    }
}
