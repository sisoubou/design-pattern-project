package fr.fges.tournament;

public class Player {
    private final String name;
    private int points;
    private int wins;

    public Player(String name) {
        this.name = name;
        this.points = 0;
        this.wins = 0;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public int getWins() {
        return wins;
    }

    void addWin() {
        this.points += 3;
        this.wins += 1;
    }

    void addLoss() {
        this.points += 1;
    }

    @Override
    public String toString() {
        String winsText = wins == 1 ? "win" : "wins";
        return name + " - " + points + " points (" + wins + " " + winsText + ")";
    }
}
