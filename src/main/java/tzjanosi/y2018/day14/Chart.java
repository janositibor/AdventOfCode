package tzjanosi.y2018.day14;

import java.util.*;

public class Chart {
    private List<Integer> scores = new ArrayList<>(List.of(3, 7));
    private int index1;
    private int index2 = 1;
    private StringBuilder scoresAsAStringBuilder = new StringBuilder();
    private int shift;

    public Chart() {
        for (int i = 0; i < scores.size(); i++) {
            scoresAsAStringBuilder.append(scores.get(i));
        }
    }

    public int calculateIndex(String stringToFind) {
        int index = -1;
        int stringLength = stringToFind.length();
        while (index == -1) {
            step(stringLength + 2);
            if (scoresAsAStringBuilder.length() >= stringLength) {
                index = scoresAsAStringBuilder.indexOf(stringToFind);
            }
        }
        return index + shift;
    }

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
            step(0);
        }
    }

    private void step(int stringLength) {
        extendScores(stringLength);
        rePositionOfIndexes();
    }

    private void rePositionOfIndexes() {
        int numberOfScores = scores.size();
        index1 = (index1 + 1 + scores.get(index1)) % numberOfScores;
        index2 = (index2 + 1 + scores.get(index2)) % numberOfScores;
    }

    private void extendScores(int stringLength) {
        int value = scores.get(index1) + scores.get(index2);
        Deque<Integer> scoresToAdd = new ArrayDeque<>();
        while (value > 9) {
            int score = value % 10;
            value = (value - score) / 10;
            scoresToAdd.addFirst(score);
        }
        scoresToAdd.addFirst(value);
        while (!scoresToAdd.isEmpty()) {
            int digit = scoresToAdd.removeFirst();
            scores.add(digit);
            scoresAsAStringBuilder.append(digit);
            int actualLength = scoresAsAStringBuilder.length();
            if (actualLength > stringLength) {
                int actualShift = actualLength - stringLength;
                shift += actualShift;
                scoresAsAStringBuilder = new StringBuilder(scoresAsAStringBuilder.substring(actualShift));
            }
        }
    }

}
