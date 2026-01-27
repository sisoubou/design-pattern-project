package fr.fges;

import java.util.Scanner;

public class ListGameCommand extends InteractiveCommand{
    public ListGameCommand(GameCollection gameCollection, Scanner scanner) {
        super(gameCollection, scanner);
    }

    @Override
    public void execute() {
        gameCollection.viewAllGames();
    }

    @Override
    public String getDescription() {
        return "List All Board Games";
    }
}
