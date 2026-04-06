package fr.fges;

import fr.fges.command.*;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private final List<Command> commands;
    private final Scanner scanner;

    public Menu(List<Command> commands, Scanner scanner) {
        this.commands = commands;
        this.scanner = scanner;
    }

    public void run() {
        while (true) {
            displayMainMenu();
            String choice = scanner.nextLine();

            try {
                int index = Integer.parseInt(choice) - 1;
                if (index >= 0 && index < commands.size()) {
                    commands.get(index).execute();
                } else {
                    System.out.println("Invalid choice. Please select a valid option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    private void displayMainMenu() {
        System.out.println("\n=== Board Game Collection ===");
        for (int i = 0; i < commands.size(); i++) {
            System.out.printf("%d. %s%n", (i + 1), commands.get(i).getDescription());
        }
        System.out.print("Select an option: ");
    }
}