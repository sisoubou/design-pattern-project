package fr.fges.command;

import fr.fges.GameCollection;
import fr.fges.GameResearch;
import fr.fges.GameUI;

import java.util.Scanner;

public class UndoCommand extends InteractiveCommand {
    private final CommandHistory history;
    private final GameUI gameUI = new GameUI();

    protected UndoCommand(GameCollection gameCollection, Scanner scanner, GameResearch gameResearch, CommandHistory history) {
        super(gameCollection, scanner, gameResearch);
        this.history = history;
    }


    @Override
    public void execute() {
        history.undo(gameUI);
    }

    @Override
    public String getDescription() {
        return "Undo Last Action:";
    }
}
