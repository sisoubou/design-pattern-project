package fr.fges.data;

import fr.fges.BoardGame;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvGameRepository implements GameRepository {
    private final String filePath;

    public CsvGameRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<BoardGame> getAll() {
        List<BoardGame> games = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // skip header
                }
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    games.add(new BoardGame(
                            parts[0],
                            Integer.parseInt(parts[1]),
                            Integer.parseInt(parts[2]),
                            parts[3]
                    ));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading from CSV: " + e.getMessage());
        }
        return games;
    }

    @Override
    public void add(BoardGame game) {
        List<BoardGame> games = getAll();
        games.add(game);
        save(games);
    }

    @Override
    public void remove(BoardGame game) {
        List<BoardGame> games = getAll();
        games.remove(game);
        save(games);
    }

    public void save(List<BoardGame> games) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("title,minPlayers,maxPlayers,category");
            writer.newLine();
            for (BoardGame game : games) {
                writer.write(game.title() + "," + game.minPlayers() + "," + game.maxPlayers() + "," + game.category());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving to CSV: " + e.getMessage());
        }
    }
}
