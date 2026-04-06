package fr.fges.command;

import fr.fges.business.BoardGame;
import fr.fges.business.RecommendGameLogic;
import fr.fges.ui.GameUI;
import fr.fges.ui.UserInput;

import java.util.Optional;

public class RecommendGameCommand implements Command {
    private final RecommendGameLogic recommendGameLogic;
    private final UserInput userInput;
    public final GameUI gameUI = new GameUI();

    public RecommendGameCommand(RecommendGameLogic recommendGameLogic, UserInput userInput) {
        this.recommendGameLogic = recommendGameLogic;
        this.userInput = userInput;
    }

    @Override
    public void execute() {
        String nbPlayerStr = userInput.getUserInput("How many players ?");
        int nbPlayer = Integer.parseInt(nbPlayerStr);

        Optional<BoardGame> game = recommendGameLogic.recommendGame(nbPlayer);
        if (game.isPresent()){
            gameUI.showRecommendedGame(game.get());
        }else{
            gameUI.showErrorNoMatch();
        }
    }

    @Override
    public String getDescription() {
        return "Recommend Game";
    }
}
