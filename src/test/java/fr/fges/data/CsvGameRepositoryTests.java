package fr.fges.samplecode;

import fr.fges.data.CsvGameRepository;
import fr.fges.BoardGame;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.List;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class CsvGameRepositoryTests {
	@Test
	void testLoadFileDoesNotExist() {
		File temp = new File("not_existing_file.csv");
		if (temp.exists()) temp.delete();
		CsvGameRepository repo = new CsvGameRepository(temp.getPath());
		List<BoardGame> games = repo.load();
		assertNotNull(games);
		assertTrue(games.isEmpty());
	}

	@Test
	void testSaveAndLoadEmptyList() {
		File temp = new File("test_empty.csv");
		temp.deleteOnExit();
		CsvGameRepository repo = new CsvGameRepository(temp.getPath());
		repo.save(List.of());
		List<BoardGame> loaded = repo.load();
		assertNotNull(loaded);
		assertTrue(loaded.isEmpty());
	}

	@Test
	void testSaveAndLoadMultipleGames() {
		File temp = new File("test_multi.csv");
		temp.deleteOnExit();
		CsvGameRepository repo = new CsvGameRepository(temp.getPath());
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
		File temp = new File("test_corrupt.csv");
		temp.deleteOnExit();
		try (BufferedWriter w = new BufferedWriter(new FileWriter(temp))) {
			w.write("title,minPlayers,maxPlayers,category\n");
			w.write("badly,formatted,line\n");
		}
		CsvGameRepository repo = new CsvGameRepository(temp.getPath());
		List<BoardGame> loaded = repo.load();
		// Should skip the bad line, so list is empty
		assertNotNull(loaded);
		assertTrue(loaded.isEmpty());
	}
}
