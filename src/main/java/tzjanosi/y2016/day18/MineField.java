package tzjanosi.y2016.day18;

import java.util.ArrayList;
import java.util.List;

public class MineField {
    private List<Boolean> start = new ArrayList<>();
    private int numberOfSafeTiles;

    public MineField(String input) {
        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '^') {
                start.add(true);
            } else {
                start.add(false);
                numberOfSafeTiles++;
            }
        }
    }

    public int calculateRows(int numberOfRows) {
        List<Boolean> actual = start;
        List<Boolean> next;
        for (int i = 0; i < numberOfRows; i++) {
            next = calculateNextRow(actual);
            actual = next;
        }
        return numberOfSafeTiles;
    }

    public String checkCalculateNextRow() {
        List<Boolean> result = calculateNextRow(start);
        return convertToString(result);
    }

    private String convertToString(List<Boolean> result) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < result.size(); i++) {
            output.append(result.get(i) ? "^" : ".");
        }
        return output.toString();
    }

    private List<Boolean> calculateNextRow(List<Boolean> input) {
        List<Boolean> output = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            boolean nextTile = isTrap(input, i);
            output.add(nextTile);
            if (!nextTile) {
                numberOfSafeTiles++;
            }
        }
        return output;
    }

    private Boolean isTrap(List<Boolean> input, int i) {
        boolean left;
        boolean right;
        if (i > 0) {
            left = input.get(i - 1);
        } else {
            left = false;
        }
        boolean center = input.get(i);
        if (i < input.size() - 1) {
            right = input.get(i + 1);
        } else {
            right = false;
        }

        return (
                (left && center && !right)
                        || (!left && center && right)
                        || (left && !center && !right)
                        || (!left && !center && right)
        );
    }
}
