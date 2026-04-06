package fr.fges;

import fr.fges.business.GameHistory;
import fr.fges.command.Command;
import fr.fges.command.CommandFactory;
import fr.fges.data.DataFactory;
import fr.fges.data.GameRepository;
import fr.fges.ui.Menu;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java -jar boardgamecollection.jar <storage-file>");
            System.out.println("Storage file must be .json or .csv");
            System.exit(1);
        }
        String fileName = args[0];
        DataFactory dataFactory = new DataFactory();
        GameRepository repository = dataFactory.createRepository(fileName);

        System.out.println("Using storage file: " + fileName);

        GameHistory gameHistory = new GameHistory();
        Scanner scanner = new Scanner(System.in);

        CommandFactory commandFactory = new CommandFactory(repository, gameHistory, scanner);
        List<Command> commands = commandFactory.createCommands();

        Menu menu = new Menu(commands, scanner);

        menu.run();
    }
}
