package fr.fges.data;

public class DataFactory {
    public GameRepository createRepository(String fileName){
        if (fileName.endsWith(".json")) {
            return new JsonGameRepository(fileName);
        } else if (fileName.endsWith(".csv")) {
            return new CsvGameRepository(fileName);
        } else {
            throw new IllegalArgumentException("Error: File must be .json or .csv");
        }
    }
}
