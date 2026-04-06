package fr.fges.command;

import fr.fges.BoardGame;
import fr.fges.GameResearch;
import fr.fges.ui.GameUI;

import java.util.List;
public class AllGamesNumberMatchCommand implements Command {
    private final GameResearch gameResearch;
    private final UserInput userInput;
    public final GameUI gameUI = new GameUI();

    public AllGamesNumberMatchCommand(GameResearch gameResearch, UserInput userInput) {
        this.gameResearch = gameResearch;
        this.userInput = userInput;
    }


    @Override
    public void execute() {
        String nbPlayersSTR = userInput.getUserInput("Number of players");
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
