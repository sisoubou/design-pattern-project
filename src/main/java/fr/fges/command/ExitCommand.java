package fr.fges.command;

public class ExitCommand implements Command {

    public ExitCommand() {
    }

    @Override
    public void execute() {
        System.out.println("Exiting the application. Goodbye!");
        System.exit(0);
    }

    @Override
    public String getDescription() {
        return "Exit";
    }
}
