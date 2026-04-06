package fr.fges.command;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExitCommandTest {
    @Test
    void executePrintsMessageAndExitsWithCodeZero() throws Exception {
        Process process = new ProcessBuilder(
                "java",
                "-cp",
                System.getProperty("java.class.path"),
                "fr.fges.command.ExitCommandLauncher"
        ).redirectErrorStream(true).start();

        int exitCode = process.waitFor();
        String output = new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8)
                .replace("\r\n", "\n");

        assertEquals(0, exitCode);
        assertTrue(output.contains("Exiting the application. Goodbye!"));
    }
}
