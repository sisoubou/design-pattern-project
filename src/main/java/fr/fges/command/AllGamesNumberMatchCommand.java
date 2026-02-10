package fr.fges.command;

import fr.fges.BoardGame;
import fr.fges.GameCollection;
import fr.fges.GameResearch;
import fr.fges.GameUI;

import java.util.List;
import java.util.Scanner;

public class AllGamesNumberMatchCommand extends InteractiveCommand {
    public final GameUI gameUI = new GameUI();

    protected AllGamesNumberMatchCommand(GameCollection gameCollection, Scanner scanner, GameResearch gameResearch) {
        super(gameCollection, scanner, gameResearch);
    }


    @Override
    public void execute() {
        String nbPlayersSTR = getUserInput("Number of players");
        Integer nbPlayers = Integer.parseInt(nbPlayersSTR);
        try {
            List<BoardGame> allGamesMatch = gameResearch.getAllGamesNumberMatch(nbPlayers);
            if (allGamesMatch.isEmpty()){
                gameUI.showErrorEmptyList();
            }else{
                gameUI.showList(allGamesMatch);
            }
        } catch (IllegalArgumentException e) {
            gameUI.showErrorNoMatch();
        }

    }

    @Override
    public String getDescription() {
        return "Games for X Players";
    }
}
