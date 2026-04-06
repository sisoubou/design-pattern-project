package fr.fges.command;

import fr.fges.GameCollection;
import fr.fges.GameUI;

public class UndoCommand implements Command {
    private final GameCollection gameCollection;
    private final GameUI gameUI = new GameUI();

    public UndoCommand(GameCollection gameCollection) {
        this.gameCollection = gameCollection;
    }

    @Override
    public void execute() {
        try {
            String resultMessage = gameCollection.undo();
            System.out.println("Undone : " + resultMessage);
        } catch (IllegalArgumentException e) {
            gameUI.showError(e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Undo Last Action";
    }
}
