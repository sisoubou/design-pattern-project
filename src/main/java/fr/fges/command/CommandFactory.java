package fr.fges.command;

import fr.fges.business.GameCollection;
import fr.fges.business.GameResearch;
import fr.fges.ui.UserInput;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommandFactory {
    private final GameCollection gameCollection;
    private final Scanner scanner;
    private final GameResearch gameResearch;

    public CommandFactory(GameCollection gameCollection, Scanner scanner, GameResearch gameResearch) {
        this.gameCollection = gameCollection;
        this.scanner = scanner;
        this.gameResearch = gameResearch;
    }

    public List<Command> createCommands() {
        List<Command> commands = new ArrayList<>();
        UserInput userInput = new UserInput(scanner);

        commands.add(new AddGameCommand(gameCollection, userInput));
        commands.add(new RemoveGameCommand(gameCollection, userInput));
        commands.add(new ListGameCommand(gameCollection));
        commands.add(new RecommendGameCommand(gameResearch, userInput));

        if (isWeekend()){
            commands.add(new SummaryCommand(gameResearch));
        }
        
        commands.add(new UndoCommand(gameCollection));
        commands.add(new AllGamesNumberMatchCommand(gameResearch, userInput));
        commands.add(new TournamentCommand(gameResearch, userInput));
        commands.add(new ExitCommand());
        
        return commands;
    }

    private Boolean isWeekend() {
        DayOfWeek today = LocalDate.now().getDayOfWeek();
        return today == DayOfWeek.SATURDAY || today == DayOfWeek.SUNDAY;
    }
}
