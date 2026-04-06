package fr.fges.command;

import fr.fges.business.BoardGame;
import fr.fges.business.GetRandomGamesLogic;
import fr.fges.ui.GameUI;

import java.util.List;

public class SummaryCommand implements Command {
    private final GetRandomGamesLogic getRandomGamesLogic;
    public final GameUI gameUI = new GameUI();

    public SummaryCommand(GetRandomGamesLogic getRandomGamesLogic) {
        this.getRandomGamesLogic = getRandomGamesLogic;
    }


    @Override
    public void execute() {

        try {
            List<BoardGame> randomGames= getRandomGamesLogic.getRandomGames(3);
            if(randomGames.isEmpty()){
                gameUI.showErrorEmptyList();
            }else {
                gameUI.showSummary(randomGames);
            }
        } catch (IllegalArgumentException e) {
            gameUI.showErrorNotEnoughGames(Integer.parseInt(e.getMessage()));
        }
    }

    @Override
    public String getDescription() {
        return "View Summary (Weekend Special!)";
    }
}
