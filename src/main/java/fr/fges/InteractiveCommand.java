package fr.fges;

import java.util.Scanner;

public abstract class InteractiveCommand implements Command{
    protected final GameCollection gameCollection;
    protected final Scanner scanner;

    protected InteractiveCommand(GameCollection gameCollection, Scanner scanner) {
        this.gameCollection = gameCollection;
        this.scanner = scanner;
    }

    public String getUserInput(String prompt) {
        // Scanner is a class in java that helps to read input from various sources like keyboard input, files, etc.
        // No new line for this one
        System.out.printf("%s: ", prompt);
        // Read input for the keyboard
        return scanner.nextLine();
    }
}
