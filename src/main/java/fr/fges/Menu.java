package fr.fges;

import fr.fges.command.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    private final Map<String, Command> commands;
    private final Scanner scanner;

    public Menu(Map<String, Command> commands, Scanner scanner) {
        this.commands = commands;
        this.scanner = scanner;
    }

    public void run() {
        while (true) {
            displayMainMenu();
            String choice = scanner.nextLine();

            if (commands.containsKey(choice)) {
                commands.get(choice).execute();
            } else {
                System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    private void displayMainMenu() {
        System.out.println("\n=== Board Game Collection ===");
        for (Map.Entry<String, Command> entry : commands.entrySet()) {
            System.out.printf("%s. %s%n", entry.getKey(), entry.getValue().getDescription());
        }
        System.out.print("Select an option: ");
    }
}