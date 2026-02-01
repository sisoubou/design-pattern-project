package fr.fges.samplecode;

import fr.fges.JsonGameRepository;
import fr.fges.BoardGame;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.List;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class JsonGameRepositoryTests {
	@Test
	void testLoadFileDoesNotExist() {
		File temp = new File("not_existing_file.json");
		if (temp.exists()) temp.delete();
		JsonGameRepository repo = new JsonGameRepository(temp.getPath());
		List<BoardGame> games = repo.load();
		assertNotNull(games);
		assertTrue(games.isEmpty());
	}

	@Test
	void testSaveAndLoadEmptyList() {
		File temp = new File("test_empty.json");
		temp.deleteOnExit();
		JsonGameRepository repo = new JsonGameRepository(temp.getPath());
		repo.save(List.of());
		List<BoardGame> loaded = repo.load();
		assertNotNull(loaded);
		assertTrue(loaded.isEmpty());
	}

	@Test
	void testSaveAndLoadMultipleGames() {
		File temp = new File("test_multi.json");
		temp.deleteOnExit();
		JsonGameRepository repo = new JsonGameRepository(temp.getPath());
		List<BoardGame> toSave = Arrays.asList(
				new BoardGame("Uno", 2, 10, "Card"),
				new BoardGame("Catan", 3, 4, "Board")
		);
		repo.save(toSave);
		List<BoardGame> loaded = repo.load();
		assertEquals(2, loaded.size());
		assertEquals("Uno", loaded.get(0).title());
		assertEquals("Catan", loaded.get(1).title());
	}

	@Test
	void testLoadCorruptedFile() throws IOException {
		File temp = new File("test_corrupt.json");
		temp.deleteOnExit();
		try (BufferedWriter w = new BufferedWriter(new FileWriter(temp))) {
			w.write("not a json file\n");
		}
		JsonGameRepository repo = new JsonGameRepository(temp.getPath());
		List<BoardGame> loaded = repo.load();
		// Should return empty list on error
		assertNotNull(loaded);
		assertTrue(loaded.isEmpty());
	}
}
