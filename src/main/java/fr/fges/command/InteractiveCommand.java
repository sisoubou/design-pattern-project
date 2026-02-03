package fr.fges.command;

import fr.fges.GameCollection;

import java.util.Scanner;

public abstract class InteractiveCommand implements Command {
    protected final GameCollection gameCollection;
    protected final Scanner scanner;

    protected InteractiveCommand(GameCollection gameCollection, Scanner scanner) {
        this.gameCollection = gameCollection;
        this.scanner = scanner;
    }

    public String getUserInput(String prompt) {
        System.out.printf("%s: ", prompt);
        return scanner.nextLine();
    }
}
