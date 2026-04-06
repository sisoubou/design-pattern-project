package fr.fges.command;

import fr.fges.BoardGame;
import fr.fges.GameResearch;
import fr.fges.ui.GameUI;

import java.util.Optional;
public class RecommendGameCommand implements Command {
    private final GameResearch gameResearch;
    private final UserInput userInput;
    public final GameUI gameUI = new GameUI();

    public RecommendGameCommand(GameResearch gameResearch, UserInput userInput) {
        this.gameResearch = gameResearch;
        this.userInput = userInput;
    }

    @Override
    public void execute() {
        String nbPlayerStr = userInput.getUserInput("How many players ?");
        int nbPlayer = Integer.parseInt(nbPlayerStr);

        Optional<BoardGame> game = gameResearch.recommendGame(nbPlayer);
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
