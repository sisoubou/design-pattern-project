package fr.fges.samplecode;

import fr.fges.Menu;
import fr.fges.GameCollection;
import fr.fges.data.GameRepository;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MenuDisplayTests {
    // Ce test vérifie l'affichage du menu avec des mocks pour les dépendances
    @Test
    void testMenuDisplayWeekdayOnly() throws Exception {
        java.time.DayOfWeek today = java.time.LocalDate.now().getDayOfWeek();
        if (today == java.time.DayOfWeek.SATURDAY || today == java.time.DayOfWeek.SUNDAY) {
            System.out.println("Test ignoré car aujourd'hui est un week-end.");
            return;
        }
        GameRepository mockRepo = mock(GameRepository.class);
        when(mockRepo.load()).thenReturn(Collections.emptyList());
        GameCollection mockCollection = new GameCollection(mockRepo);
        Menu menu = new Menu(mockCollection);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(out));
        var m = Menu.class.getDeclaredMethod("displayMainMenu");
        m.setAccessible(true);
        m.invoke(menu);
        System.setOut(old);
        String output = out.toString();
        assertTrue(output.contains("5. Exit"), "Le menu semaine doit contenir '5. Exit'");
        assertFalse(output.contains("Summary"), "Le menu semaine ne doit pas contenir 'Summary'");
    }

    @Test
    void testMenuDisplayWeekendOnly() throws Exception {
        java.time.DayOfWeek today = java.time.LocalDate.now().getDayOfWeek();
        if (!(today == java.time.DayOfWeek.SATURDAY || today == java.time.DayOfWeek.SUNDAY)) {
            System.out.println("Test ignoré car aujourd'hui n'est pas un week-end.");
            return;
        }
        GameRepository mockRepo = mock(GameRepository.class);
        when(mockRepo.load()).thenReturn(Collections.emptyList());
        GameCollection mockCollection = new GameCollection(mockRepo);
        Menu menu = new Menu(mockCollection);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(out));
        var m = Menu.class.getDeclaredMethod("displayMainMenu");
        m.setAccessible(true);
        m.invoke(menu);
        System.setOut(old);
        String output = out.toString();
        assertTrue(output.contains("5. Summary"), "Le menu week-end doit contenir '5. Summary'");
        assertTrue(output.contains("6. Exit"), "Le menu week-end doit contenir '6. Exit'");
    }
}
