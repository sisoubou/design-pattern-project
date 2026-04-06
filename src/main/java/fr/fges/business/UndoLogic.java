package fr.fges.business;

public class UndoLogic {
    private final GameHistory history;

    public UndoLogic(GameHistory history) {
        this.history = history;
    }

    public String undo() {
        if (history.isEmpty()) {
            throw new IllegalArgumentException("Nothing to undo.");
        }
        UndoAction lastAction = history.pop();
        return lastAction.undo();
    }
}
