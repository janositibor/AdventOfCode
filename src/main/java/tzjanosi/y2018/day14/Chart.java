package tzjanosi.y2018.day14;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Chart {
    private List<Integer> scores = new ArrayList<>(List.of(3, 7));
    private int index1;
    private int index2 = 1;

    public String calculateScore(int numberOfDigitsToSkip, int numberOfEvaluatedDigits) {
        buildScoresToTheLength(numberOfDigitsToSkip + numberOfEvaluatedDigits);
        return getValue(numberOfDigitsToSkip, numberOfEvaluatedDigits);
    }

    private String getValue(int numberOfDigitsToSkip, int numberOfEvaluatedDigits) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < numberOfEvaluatedDigits; i++) {
            int index = numberOfDigitsToSkip + i;
            output.append(scores.get(index));
        }
        return output.toString();
    }

    private void buildScoresToTheLength(int desiredLength) {
        while (scores.size() < desiredLength) {
            step();
        }
    }

    private void step() {
        extendScores();
        rePositionOfIndexes();
    }

    private void rePositionOfIndexes() {
        int numberOfScores = scores.size();
        index1 = (index1 + 1 + scores.get(index1)) % numberOfScores;
        index2 = (index2 + 1 + scores.get(index2)) % numberOfScores;
    }

    private void extendScores() {
        int value = scores.get(index1) + scores.get(index2);
        Deque<Integer> scoresToAdd = new ArrayDeque<>();
        while (value > 9) {
            int score = value % 10;
            value = (value - score) / 10;
            scoresToAdd.addFirst(score);
        }
        scoresToAdd.addFirst(value);
        while (!scoresToAdd.isEmpty()) {
            scores.add(scoresToAdd.removeFirst());
        }
    }

}
