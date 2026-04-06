package fr.fges.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DataFactoryTest {
    private final DataFactory factory = new DataFactory();

    @Test
    void createsJsonRepositoryForJsonFile() {
        assertInstanceOf(JsonGameRepository.class, factory.createRepository("games.json"));
    }

    @Test
    void createsCsvRepositoryForCsvFile() {
        assertInstanceOf(CsvGameRepository.class, factory.createRepository("games.csv"));
    }

    @Test
    void rejectsUnsupportedExtensions() {
        assertThrows(IllegalArgumentException.class, () -> factory.createRepository("games.txt"));
    }
}
