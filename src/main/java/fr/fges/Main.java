package fr.fges;

import fr.fges.data.CsvGameRepository;
import fr.fges.data.GameRepository;
import fr.fges.data.JsonGameRepository;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java -jar boardgamecollection.jar <storage-file>");
            System.out.println("Storage file must be .json or .csv");
            System.exit(1);
        }

        String fileName = args[0];
        GameRepository repository = null;

        // Check file extension
        if (fileName.endsWith(".json")) {
            repository = new JsonGameRepository(fileName);
        } else if (fileName.endsWith(".csv")) {
            repository = new CsvGameRepository(fileName);
        } else {
            System.out.println("Error: File must be .json or .csv");
            System.exit(1);
        }

        System.out.println("Using storage file: " + fileName);

        GameCollection collection = new GameCollection(repository);
        Menu menu = new Menu(collection);

        menu.run();
    }
}
