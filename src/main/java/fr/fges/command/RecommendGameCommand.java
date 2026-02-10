package fr.fges.command;

import fr.fges.BoardGame;
import fr.fges.GameCollection;
import fr.fges.GameResearch;
import fr.fges.GameUI;

import java.util.Optional;
import java.util.Scanner;

public class RecommendGameCommand extends InteractiveCommand {
    public final GameUI gameUI = new GameUI();

    protected RecommendGameCommand(GameCollection gameCollection, Scanner scanner, GameResearch gameResearch) {
        super(gameCollection, scanner, gameResearch);
    }

    @Override
    public void execute() {
        String nbPlayerStr = getUserInput("How many players ?");
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
        return "Recommend Board Game";
    }
}
