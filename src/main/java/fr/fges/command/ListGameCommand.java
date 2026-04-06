package fr.fges.command;

import fr.fges.business.ViewAllGamesLogic;
import fr.fges.ui.GameUI;

public class ListGameCommand implements Command {
    private final ViewAllGamesLogic viewAllGamesLogic;
    public final GameUI gameUI = new GameUI();

    public ListGameCommand(ViewAllGamesLogic viewAllGamesLogic) {
        this.viewAllGamesLogic = viewAllGamesLogic;
    }

    @Override
    public void execute() {
        var games = viewAllGamesLogic.viewAllGames();
        gameUI.showList(games);
    }

    @Override
    public String getDescription() {
        return "List All Board Games";
    }
}
