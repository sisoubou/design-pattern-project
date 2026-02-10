package fr.fges.command;

import fr.fges.GameCollection;
import fr.fges.GameResearch;

import java.util.Scanner;

public abstract class InteractiveCommand implements Command {
    protected final GameCollection gameCollection;
    protected final Scanner scanner;
    protected final GameResearch gameResearch;

    protected InteractiveCommand(GameCollection gameCollection, Scanner scanner, GameResearch gameResearch) {
        this.gameCollection = gameCollection;
        this.gameResearch = gameResearch;
        this.scanner = scanner;
    }

    public String getUserInput(String prompt) {
        System.out.printf("%s: ", prompt);
        return scanner.nextLine();
    }
}
