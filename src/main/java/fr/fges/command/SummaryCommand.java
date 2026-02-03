package fr.fges.command;

import fr.fges.BoardGame;
import fr.fges.GameCollection;
import fr.fges.GameUI;

import java.util.List;
import java.util.Scanner;

public class SummaryCommand extends InteractiveCommand {
    public final GameUI gameUI = new GameUI();

    public SummaryCommand(GameCollection gameCollection, Scanner scanner) {
        super(gameCollection, scanner);
    }

    @Override
    public void execute() {

        try {
            List<BoardGame> randomGames= gameCollection.getRandomGames(3);
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
        return "Summary (Weekend Special!)";
    }
}
