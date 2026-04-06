package fr.fges.business;

import java.util.Stack;

public class GameHistory {
    private final Stack<UndoAction> history = new Stack<>();

    public void push(UndoAction action) {
        history.push(action);
    }

    public UndoAction pop() {
        return history.pop();
    }

    public boolean isEmpty() {
        return history.isEmpty();
    }
}
