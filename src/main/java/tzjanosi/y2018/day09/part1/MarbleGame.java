package tzjanosi.y2018.day09.part1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class MarbleGame {
    private static final int STAR = 23;
    private int numberOfPlayers;
    private int lastMarble;
    private List<Integer> marbles = new LinkedList<>(List.of(0));
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
        int index = 0;
        for (int i = 1; i <= lastMarble; i++) {
            if (i % STAR == 0) {
                index = starAction(index, i);
            } else {
                index = addMarble(index, i);
            }
        }
    }

    private int starAction(int index, int value) {
        int actualPlayer = value % numberOfPlayers;
        long result = results.get(actualPlayer);
        int actualIndex = getActualIndexForStarAction(index);
        long gained = value + marbles.remove(actualIndex);
        results.set(actualPlayer, result + gained);
        return actualIndex;
    }

    private int getActualIndexForStarAction(int index) {
        int result = index - 7;
        if (result > 0) {
            return result;
        }
        return (result + marbles.size());
    }

    private int addMarble(int index, int value) {
        int actualIndex = getActualIndexForAdd(index);
        marbles.add(actualIndex, value);
        return actualIndex;
    }

    private int getActualIndexForAdd(int index) {
        if (index + 2 > marbles.size()) {
            return 1;
        } else {
            return (index + 2);
        }
    }

}
