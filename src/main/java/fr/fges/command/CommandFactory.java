package fr.fges.command;

import fr.fges.business.*;
import fr.fges.data.GameRepository;
import fr.fges.ui.UserInput;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommandFactory {
    private final GameRepository gameRepository;
    private final GameHistory gameHistory;
    private final Scanner scanner;

    public CommandFactory(GameRepository gameRepository, GameHistory gameHistory, Scanner scanner) {
        this.gameRepository = gameRepository;
        this.gameHistory = gameHistory;
        this.scanner = scanner;
    }

    public List<Command> createCommands() {
        List<Command> commands = new ArrayList<>();
        UserInput userInput = new UserInput(scanner);

        commands.add(new AddGameCommand(new AddGameLogic(gameRepository, gameHistory), userInput));
        commands.add(new RemoveGameCommand(new RemoveGameLogic(gameRepository, gameHistory), userInput));
        commands.add(new ListGameCommand(new ViewAllGamesLogic(gameRepository)));
        commands.add(new RecommendGameCommand(new RecommendGameLogic(gameRepository), userInput));

        if (isWeekend()){
            commands.add(new SummaryCommand(new GetRandomGamesLogic(gameRepository)));
        }

        commands.add(new UndoCommand(new UndoLogic(gameHistory)));
        
        ViewAllGamesLogic viewAllGamesLogic = new ViewAllGamesLogic(gameRepository);
        commands.add(new AllGamesNumberMatchCommand(new GetAllGamesNumberMatchLogic(gameRepository, viewAllGamesLogic), userInput));
        commands.add(new TournamentCommand(new GetAllGamesNumberMatchLogic(gameRepository, viewAllGamesLogic), userInput));
        commands.add(new ExitCommand());

        return commands;
    }

    private Boolean isWeekend() {
        DayOfWeek today = LocalDate.now().getDayOfWeek();
        return today == DayOfWeek.SATURDAY || today == DayOfWeek.SUNDAY;
    }
}
