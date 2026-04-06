package fr.fges.command;

import java.util.Scanner;

public class UserInput {
    private final Scanner scanner;

    public UserInput(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getUserInput(String prompt) {
        System.out.printf("%s: ", prompt);
        return scanner.nextLine();
    }
}
