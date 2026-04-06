package fr.fges.ui;

import fr.fges.Menu;
import fr.fges.command.Command;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MenuTest {
    @Test
    void menuDisplaysAndExecutesCommand() {
        Command mockCommand = mock(Command.class);
        List<Command> commands = new ArrayList<>();
        commands.add(mockCommand);
        Scanner scanner = new Scanner("1\n");
        Menu menu = new Menu(commands, scanner);
        // On exécute une seule itération pour le test (sinon boucle infinie)
        // On simule l'appel direct à displayMainMenu et à la commande
        menu.run(); // Peut nécessiter adaptation si la méthode run() boucle infiniment
        verify(mockCommand, atLeastOnce()).execute();
    }

    @Test
    void menuHandlesInvalidChoice() {
        Command mockCommand = mock(Command.class);
        List<Command> commands = new ArrayList<>();
        commands.add(mockCommand);
        Scanner scanner = new Scanner("invalid\n1\n");
        Menu menu = new Menu(commands, scanner);
        // On s'attend à ce que la commande soit exécutée après un mauvais choix
        menu.run();
        verify(mockCommand, atLeastOnce()).execute();
    }
}
