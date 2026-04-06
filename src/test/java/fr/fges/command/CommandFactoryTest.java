package fr.fges.command;

import fr.fges.business.GameHistory;
import fr.fges.support.InMemoryGameRepository;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommandFactoryTest {
    @Test
    void createCommandsBuildsTheExpectedMenuInOrder() {
        CommandFactory factory = new CommandFactory(
                new InMemoryGameRepository(),
                new GameHistory(),
                new Scanner("")
        );

        List<Command> commands = factory.createCommands();
        boolean weekend = LocalDate.now().getDayOfWeek() == DayOfWeek.SATURDAY
                || LocalDate.now().getDayOfWeek() == DayOfWeek.SUNDAY;

        assertFalse(commands.isEmpty());
        assertInstanceOf(AddGameCommand.class, commands.get(0));
        assertInstanceOf(RemoveGameCommand.class, commands.get(1));
        assertInstanceOf(ListGameCommand.class, commands.get(2));
        assertInstanceOf(RecommendGameCommand.class, commands.get(3));
        assertEquals(weekend, commands.stream().anyMatch(SummaryCommand.class::isInstance));
        assertInstanceOf(UndoCommand.class, commands.get(weekend ? 5 : 4));
        assertInstanceOf(AllGamesNumberMatchCommand.class, commands.get(weekend ? 6 : 5));
        assertInstanceOf(TournamentCommand.class, commands.get(weekend ? 7 : 6));
        assertInstanceOf(ExitCommand.class, commands.getLast());
        assertEquals(weekend ? 9 : 8, commands.size());
    }
}
