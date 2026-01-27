package fr.fges;

import java.util.Scanner;

public class ExitCommand extends InteractiveCommand{
    public ExitCommand(GameCollection gameCollection, Scanner scanner) {
        super(gameCollection, scanner);
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
