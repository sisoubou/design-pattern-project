package fr.fges.command;

import fr.fges.GameCollection;
import fr.fges.GameUI;

public class ListGameCommand extends InteractiveCommand {
    public final GameUI gameUI = new GameUI();
    public ListGameCommand(GameCollection gameCollection) {
        super(gameCollection, null);
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
