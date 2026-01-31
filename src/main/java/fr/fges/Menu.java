package fr.fges;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    private final Map<String, Command> commands = new HashMap<>();
    private final Scanner scanner;
    private final boolean isWeekend;

    public Menu(GameCollection collection) {
        this.scanner = new Scanner(System.in);

        DayOfWeek today = LocalDate.now().getDayOfWeek();
        this.isWeekend = (today == DayOfWeek.SATURDAY || today == DayOfWeek.SUNDAY);

        commands.put("1", new AddGameCommand(collection, scanner));
        commands.put("2", new RemoveGameCommand(collection, scanner));
        commands.put("3", new ListGameCommand(collection));
        commands.put("4", new RecommendGameCommand(collection, scanner));

        if (isWeekend) {
            commands.put("5", new SummaryCommand(collection, scanner));
            commands.put("6", new ExitCommand());
        } else {
            commands.put("5", new ExitCommand());
        }

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
        System.out.println("1. Add Board Game");
        System.out.println("2. Remove Board Game");
        System.out.println("3. List All Board Games");
        System.out.println("4. Recommend Game");

        if (isWeekend) {
            System.out.println("5. Summary (Weekend Special!)");
            System.out.println("6. Exit");
        } else {
            System.out.println("5. Exit");
        }
        System.out.print("Select an option: ");
    }
}