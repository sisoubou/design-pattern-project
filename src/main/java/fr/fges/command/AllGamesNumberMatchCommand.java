package fr.fges.command;

import fr.fges.business.BoardGame;
import fr.fges.business.GetAllGamesNumberMatchLogic;
import fr.fges.ui.GameUI;
import fr.fges.ui.UserInput;

import java.util.List;
public class AllGamesNumberMatchCommand implements Command {
    private final GetAllGamesNumberMatchLogic getAllGamesNumberMatchLogic;
    private final UserInput userInput;
    public final GameUI gameUI = new GameUI();

    public AllGamesNumberMatchCommand(GetAllGamesNumberMatchLogic getAllGamesNumberMatchLogic, UserInput userInput) {
        this.getAllGamesNumberMatchLogic = getAllGamesNumberMatchLogic;
        this.userInput = userInput;
    }


    @Override
    public void execute() {
        String nbPlayersSTR = userInput.getUserInput("Number of players");
        Integer nbPlayers = Integer.parseInt(nbPlayersSTR);
        try {
            List<BoardGame> allGamesMatch = getAllGamesNumberMatchLogic.getAllGamesNumberMatch(nbPlayers);
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
