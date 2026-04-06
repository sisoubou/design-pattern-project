package fr.fges.command;

import fr.fges.BoardGame;
import fr.fges.GameResearch;
import fr.fges.GameUI;

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
