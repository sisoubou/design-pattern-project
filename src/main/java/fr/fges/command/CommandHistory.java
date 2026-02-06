package fr.fges.command;

import fr.fges.GameUI;

import java.util.Stack;

public class CommandHistory {
    private final Stack<UndoAction> history = new Stack<>();

    public void push (UndoAction action){
        history.push(action);
    }

    public void undo(GameUI gameUI) {
        if (history.isEmpty()) {
            gameUI.showError("No actions to undo.");
            return;
        } else {
            UndoAction lastAction = history.pop();
            lastAction.undo();
            System.out.println("Undone : Removed " + game.lastAddedGameTitle + " from collection");
        }
    }


}
