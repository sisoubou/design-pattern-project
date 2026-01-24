package fr.fges;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonGameRepository implements GameRepository{
    private final String filepath;
    private final ObjectMapper mapper = new ObjectMapper();

    public JsonGameRepository(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public List<BoardGame> load() {
        File file = new File(filepath);
        try {
            return mapper.readValue(file, new TypeReference<List<BoardGame>>() {});
        } catch (IOException e) {
            System.out.println("Error loading from JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void save(List<BoardGame> games) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filepath), games);
        } catch (IOException e) {
            System.out.println("Error saving to JSON: " + e.getMessage());
        }
    }
}
