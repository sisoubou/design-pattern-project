package fr.fges.support;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public final class ConsoleCapture implements AutoCloseable {
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private final ByteArrayOutputStream stdout = new ByteArrayOutputStream();
    private final ByteArrayOutputStream stderr = new ByteArrayOutputStream();

    public ConsoleCapture() {
        System.setOut(new PrintStream(stdout, true, StandardCharsets.UTF_8));
        System.setErr(new PrintStream(stderr, true, StandardCharsets.UTF_8));
    }

    public String stdout() {
        return normalize(stdout);
    }

    public String stderr() {
        return normalize(stderr);
    }

    private String normalize(ByteArrayOutputStream output) {
        return new String(output.toByteArray(), StandardCharsets.UTF_8).replace("\r\n", "\n");
    }

    @Override
    public void close() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
}
