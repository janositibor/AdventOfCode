package tzjanosi.y2018.day09.part2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MarbleGame {
    private static final int STAR = 23;
    private int numberOfPlayers;
    private int lastMarble;
    private List<Long> results = new ArrayList<>();

    public MarbleGame(int numberOfPlayers, int lastMarble) {
        this.numberOfPlayers = numberOfPlayers;
        this.lastMarble = lastMarble;
        buildResults();
        buildGame();
    }

    private void buildResults() {
        for (int i = 0; i < numberOfPlayers; i++) {
            results.add(0L);
        }
    }

    public long findWinnerScore() {
        return results.stream().max(Comparator.naturalOrder()).orElseThrow(() -> new IllegalStateException("Empty result list!"));
    }

    private void buildGame() {
        Marble zero = new Marble(0);
        Marble actual = zero;
        for (int i = 1; i <= lastMarble; i++) {
            if (i % STAR == 0) {
                actual = starAction(actual, i);
            } else {
                actual = addMarble(actual, i);
            }
        }
    }

    private Marble starAction(Marble input, int value) {
        Marble actual = input;
        int actualPlayer = value % numberOfPlayers;
        long result = results.get(actualPlayer);
        for (int i = 0; i < 7; i++) {
            actual = actual.getLeft();
        }
        long gained = value + actual.getValue();
        results.set(actualPlayer, result + gained);
        return actual.remove();
    }

    private Marble addMarble(Marble actual, int value) {
        Marble marbleToAdd = new Marble(value);
        actual.getRight().add(marbleToAdd);
        return marbleToAdd;
    }
}
