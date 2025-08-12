package tzjanosi.y2016.day19;

import java.util.*;

public class Game {
    private List<Integer> players = new ArrayList<>();

    public Game(int numberOfPlayers) {
        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(i + 1);
        }
    }

    public int findTheWinner() {
        while (players.size() > 1) {
            round();
        }
        return players.get(0);
    }

    private void round() {
        Set<Integer> playersToRemove = new HashSet<>();
        int actualNumberOfPlayers = players.size();
        for (int i = 0; i < players.size() - 1; i += 2) {
            playersToRemove.add(players.get(i + 1));
        }
        if (actualNumberOfPlayers % 2 == 1) {
            playersToRemove.add(players.get(0));
        }
        players.removeAll(playersToRemove);
    }
}
