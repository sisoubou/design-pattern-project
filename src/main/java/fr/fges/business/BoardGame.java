package fr.fges.business;

public record BoardGame(
        String title,
        int minPlayers,
        int maxPlayers,
        String category
) {
}
