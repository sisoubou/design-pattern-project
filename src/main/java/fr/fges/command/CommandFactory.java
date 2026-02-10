package fr.fges.command;

import fr.fges.GameCollection;
import fr.fges.GameResearch;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
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

    public Map<String, Command> createCommands() {
        Map<String, Command> commands = new LinkedHashMap<>();
        CommandHistory history = new CommandHistory();

        commands.put("1", new AddGameCommand(gameCollection, scanner, gameResearch, history));
        commands.put("2", new RemoveGameCommand(gameCollection, scanner, gameResearch, history));
        commands.put("3", new ListGameCommand(gameCollection,null, gameResearch));
        commands.put("4", new RecommendGameCommand(gameCollection, scanner, gameResearch));

        if (isWeekend()){
            commands.put("5", new SummaryCommand(gameCollection, scanner, gameResearch));
            commands.put("6", new UndoCommand(gameCollection, scanner, gameResearch, history));
            commands.put("7", new AllGamesNumberMatchCommand(gameCollection, scanner, gameResearch));
            commands.put("8", new ExitCommand(null, null, null));
            return commands;
        }else {
            commands.put("5", new UndoCommand(gameCollection, scanner, gameResearch, history));
            commands.put("6", new AllGamesNumberMatchCommand(gameCollection, scanner, gameResearch));
            commands.put("7", new ExitCommand(null, null, null));
            return commands;
        }
    }

    private Boolean isWeekend(){
        DayOfWeek today = LocalDate.now().getDayOfWeek();
        return today == DayOfWeek.SATURDAY || today == DayOfWeek.SUNDAY;
    }
}
