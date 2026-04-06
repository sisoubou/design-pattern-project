package fr.fges;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {
    @Test
    void mainWithoutArgumentsPrintsUsageAndExitsWithCodeOne() throws Exception {
        Process process = new ProcessBuilder("java", "-cp", System.getProperty("java.class.path"), "fr.fges.Main")
                .redirectErrorStream(true)
                .start();

        int exitCode = process.waitFor();
        String output = new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8)
                .replace("\r\n", "\n");

        assertEquals(1, exitCode);
        assertTrue(output.contains("Usage: java -jar boardgamecollection.jar <storage-file>"));
        assertTrue(output.contains("Storage file must be .json or .csv"));
    }
}
