package fr.fges.command;

import fr.fges.GameCollection;
import fr.fges.GameResearch;

import java.util.Scanner;

public class ExitCommand extends InteractiveCommand {


    protected ExitCommand(GameCollection gameCollection, Scanner scanner, GameResearch gameResearch) {
        super(gameCollection, scanner, gameResearch);
    }

    @Override
    public void execute() {
        System.out.println("Exiting the application. Goodbye!");
        System.exit(0);
    }

    @Override
    public String getDescription() {
        return "Exit";
    }
}
