package fr.fges;

import java.util.Scanner;

public class RecommendGameCommand extends InteractiveCommand{
    public RecommendGameCommand(GameCollection gameCollection, Scanner scanner) {
        super(gameCollection, scanner);
    }

    @Override
    public void execute() {
        String nbPlayerStr = getUserInput("How many players ?");
        int nbPlayer = Integer.parseInt(nbPlayerStr);
        gameCollection.recommendGame(nbPlayer);
    }

    @Override
    public String getDescription() {
        return "Recommend Board Game";
    }
}
