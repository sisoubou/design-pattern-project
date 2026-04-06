package fr.fges.command;

import fr.fges.GameCollection;
import fr.fges.ui.GameUI;

public class ListGameCommand implements Command {
    private final GameCollection gameCollection;
    public final GameUI gameUI = new GameUI();

    public ListGameCommand(GameCollection gameCollection) {
        this.gameCollection = gameCollection;
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
