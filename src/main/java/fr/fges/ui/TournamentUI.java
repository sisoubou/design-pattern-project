package fr.fges.ui;

public interface TournamentUI {
    void onMatchStart(int currentMatch, int totalMatches, String player1, String player2);

    void onKingRemains(String playerName);

    void onNewKing(String playerName);
}
