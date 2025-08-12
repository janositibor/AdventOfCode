package tzjanosi.y2016.day19;

import java.util.*;

public class Game {
    private List<Integer> players = new ArrayList<>();

    public Game(int numberOfPlayers) {
        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(i + 1);
        }
    }

    public int findTheWinnerPart2() {
        int thief = 1;
        while (players.size() > 1) {
            thief = roundPart2(thief);
        }
        return players.get(0);
    }

    private int roundPart2(int fromValue) {
        Set<Integer> playersToRemove = new HashSet<>();
        int actualNumberOfPlayers = players.size();
        int shift = actualNumberOfPlayers / 2;
        int from = players.indexOf(fromValue);

        for (int i = from; i < from + shift; i += 3) {
            int indexToRemove = (i + shift);
            playersToRemove.add(players.get((indexToRemove) % actualNumberOfPlayers));
            if (actualNumberOfPlayers % 2 == 0 && actualNumberOfPlayers > 2) {
                playersToRemove.add(players.get((indexToRemove + 1) % actualNumberOfPlayers));
            }
            if (actualNumberOfPlayers % 2 == 1 && actualNumberOfPlayers > 3) {
                playersToRemove.add(players.get((indexToRemove + 2) % actualNumberOfPlayers));
            }
        }
        int counter = from + playersToRemove.size();
        while (playersToRemove.contains(players.get((counter) % actualNumberOfPlayers))) {
            counter++;
        }
        int output = players.get((counter) % actualNumberOfPlayers);
        players.removeAll(playersToRemove);
        return output;
    }

    public int findTheWinnerPart1() {
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
