package fr.fges.command;

import fr.fges.GameCollection;
import fr.fges.GameResearch;
import fr.fges.GameUI;

import java.util.Scanner;

public class ListGameCommand extends InteractiveCommand {
    public final GameUI gameUI = new GameUI();

    protected ListGameCommand(GameCollection gameCollection, Scanner scanner, GameResearch gameResearch) {
        super(gameCollection, scanner, gameResearch);
    }

    @Override
    public void execute() {
        var games = gameCollection.viewAllGames();
        gameUI.showList(games);
    }

    @Override
    public String getDescription() {
        return "List All Board Games";
    }
}
