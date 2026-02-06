package fr.fges.command;

import fr.fges.GameCollection;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandFactory {
    private final GameCollection gameCollection;
    private final Scanner scanner;

    public CommandFactory(GameCollection gameCollection, Scanner scanner) {
        this.gameCollection = gameCollection;
        this.scanner = scanner;
    }

    public Map<String, Command> createCommands() {
        Map<String, Command> commands = new LinkedHashMap<>();

        commands.put("1", new AddGameCommand(gameCollection, scanner));
        commands.put("2", new RemoveGameCommand(gameCollection, scanner));
        commands.put("3", new ListGameCommand(gameCollection));
        commands.put("4", new RecommendGameCommand(gameCollection, scanner));

        if (isWeekend()){
            commands.put("5", new SummaryCommand(gameCollection, scanner));
            commands.put("6", new UndoCommand(gameCollection, scanner, new CommandHistory()));
            commands.put("8", new ExitCommand());
            return commands;
        }else {
            commands.put("5", new UndoCommand(gameCollection, scanner, new CommandHistory()));
            commands.put("7", new ExitCommand());
            return commands;
        }
    }

    private Boolean isWeekend(){
        DayOfWeek today = LocalDate.now().getDayOfWeek();
        return today == DayOfWeek.SATURDAY || today == DayOfWeek.SUNDAY;
    }
}
