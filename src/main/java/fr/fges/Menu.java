package fr.fges;

import java.util.Scanner;

public class Menu {
    private final GameCollection collection;
    private final Scanner scanner;

    public Menu(GameCollection collection) {
        this.collection = collection;
        this.scanner = new Scanner(System.in);
    }

    public String getUserInput(String prompt) {
        // Scanner is a class in java that helps to read input from various sources like keyboard input, files, etc.
        // No new line for this one
        System.out.printf("%s: ", prompt);
        // Read input for the keyboard
        return scanner.nextLine();
    }

    public void run(){
        while (true) {
            displayMainMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> handleAddGame();
                case "2" -> handleRemoveGame();
                case "3" -> handleListGames();
                case "4" -> handleExit();
                default -> System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    public void displayMainMenu() {
        System.out.println("""
                === Board Game Collection ===
                1. Add Board Game
                2. Remove Board Game
                3. List All Board Games
                4. Exit
                Please select an option (1-4):
                """);
    }

    public void handleAddGame() {
        String title = getUserInput("Title");
        String minPlayersStr = getUserInput("Minimum Players");
        String maxPlayersStr = getUserInput("Maximum Players");
        String category = getUserInput("Category (e.g., fantasy, cooperative, family, strategy)");

        int minPlayers = Integer.parseInt(minPlayersStr);
        int maxPlayers = Integer.parseInt(maxPlayersStr);

        BoardGame game = new BoardGame(title, minPlayers, maxPlayers, category);

        collection.addGame(game);
        System.out.println("Board game added successfully.");
    }


    public void handleRemoveGame() {
        String title = getUserInput("Title of game to remove");

        // get games from the collection, find the one that matches the title given by the user and remove
        var games = collection.getGames();

        for (BoardGame game : games) {
            if (game.title().equals(title)) {
                collection.removeGame(game);
                System.out.println("Board game removed successfully.");
                return;
            }
        }
        System.out.println("No board game found with that title.");
    }

    public void handleListGames() {
        collection.viewAllGames();
    }

    public void handleExit() {
        System.out.println("Exiting the application. Goodbye!");
        System.exit(0);
    }

}
