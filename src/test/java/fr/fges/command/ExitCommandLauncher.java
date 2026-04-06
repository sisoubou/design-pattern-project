package fr.fges.command;

public final class ExitCommandLauncher {
    private ExitCommandLauncher() {
    }

    public static void main(String[] args) {
        new ExitCommand().execute();
    }
}
