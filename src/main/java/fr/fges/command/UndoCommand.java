package fr.fges.command;

import fr.fges.business.UndoLogic;
import fr.fges.ui.GameUI;

public class UndoCommand implements Command {
    private final UndoLogic undoLogic;
    private final GameUI gameUI = new GameUI();

    public UndoCommand(UndoLogic undoLogic) {
        this.undoLogic = undoLogic;
    }

    @Override
    public void execute() {
        try {
            String resultMessage = undoLogic.undo();
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
