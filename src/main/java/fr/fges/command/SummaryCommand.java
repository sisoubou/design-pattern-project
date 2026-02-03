package fr.fges.command;

import fr.fges.GameCollection;

import java.util.Scanner;

public class SummaryCommand extends InteractiveCommand {
    protected SummaryCommand(GameCollection gameCollection, Scanner scanner) {
        super(gameCollection, scanner);
    }

    @Override
    public void execute() {
        gameCollection.getRandomGames(3);
    }

    @Override
    public String getDescription() {
        return "Summary (Weekend Special!)";
    }
}
