package fr.fges.tournament;

import fr.fges.command.UserInput;
import fr.fges.ui.TournamentUI;

import java.util.List;

public interface Tournament {
    void playTournament(List<Player> players, UserInput userInput, TournamentUI ui);
}
