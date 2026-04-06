package fr.fges.command;

import fr.fges.GameUI;

public class UndoCommand implements Command {
    private final CommandHistory history;
    private final GameUI gameUI = new GameUI();

    public UndoCommand(CommandHistory history) {
        this.history = history;
    }

    @Override
    public void execute() {
        history.undo(gameUI);
    }

    @Override
    public String getDescription() {
        return "Undo Last Action";
    }
}
