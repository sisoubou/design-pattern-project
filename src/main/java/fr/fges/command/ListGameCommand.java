package fr.fges.command;

import fr.fges.GameCollection;

public class ListGameCommand extends InteractiveCommand {
    public ListGameCommand(GameCollection gameCollection) {
        super(gameCollection, null);
    }

    @Override
    public void execute() {
        gameCollection.viewAllGames();
    }

    @Override
    public String getDescription() {
        return "List All Board Games";
    }
}
