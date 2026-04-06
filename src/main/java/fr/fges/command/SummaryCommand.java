package fr.fges.command;

import fr.fges.business.BoardGame;
import fr.fges.business.GameResearch;
import fr.fges.ui.GameUI;

import java.util.List;
public class SummaryCommand implements Command {
    private final GameResearch gameResearch;
    public final GameUI gameUI = new GameUI();

    public SummaryCommand(GameResearch gameResearch) {
        this.gameResearch = gameResearch;
    }


    @Override
    public void execute() {

        try {
            List<BoardGame> randomGames= gameResearch.getRandomGames(3);
            if(randomGames.isEmpty()){
                gameUI.showErrorEmptyList();
            }else {
                gameUI.showSummary(randomGames);
            }
        } catch (IllegalArgumentException e) {
            gameUI.showErrorNotEnoughGames(Integer.valueOf(String.valueOf(e)));
        }
    }

    @Override
    public String getDescription() {
        return "View Summary (Weekend Special!)";
    }
}
