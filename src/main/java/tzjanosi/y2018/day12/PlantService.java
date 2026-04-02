package tzjanosi.y2018.day12;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PlantService {
    private Map<String, Character> rules = new ConcurrentHashMap<>();
    private String initialState;
    private int shift;

    public PlantService(List<String> input) {
        processinput(input);
    }

    public long quickPotCounter(long steps) {
        long start = 1562212;
        long increment = 780000;
        return (start + increment * (steps - 20000) / 10000);
    }

    public int potCounter(int steps) {
        String plants = applyRules(steps);
        return calculatePots(plants);
    }

    private int calculatePots(String input) {
        int output = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '#') {
                output += (i + shift);
            }
        }
        return output;
    }

    private String applyRules(int steps) {
        StringBuilder actual = new StringBuilder(initialState);
        for (int i = 0; i < steps; i++) {
            actual = extend(actual);
            actual = spread(actual);
        }
        return actual.toString();
    }

    private StringBuilder extend(StringBuilder input) {
        StringBuilder actual = new StringBuilder(input);
        int firstIndex = actual.indexOf("#");
        while (firstIndex < 3) {
            actual = extendFirst(actual);
            firstIndex = actual.indexOf("#");
        }
        while (firstIndex > 3) {
            actual = shrinkFirst(actual);
            firstIndex = actual.indexOf("#");
        }
        int lastIndex = actual.lastIndexOf("#");
        if (actual.length() - 1 - lastIndex > 3) {
            actual = shrinkLast(actual, lastIndex);
        }
        while (actual.length() - 1 - lastIndex < 3) {
            actual = extendLast(actual);
            lastIndex = actual.lastIndexOf("#");
        }
        return actual;
    }

    private StringBuilder extendLast(StringBuilder actual) {
        return actual.append('.');
    }

    private StringBuilder shrinkLast(StringBuilder actual, int lastIndex) {
        return new StringBuilder(actual.substring(0, lastIndex + 2));
    }

    private StringBuilder shrinkFirst(StringBuilder actual) {
        shift++;
        return new StringBuilder(actual.substring(1));
    }

    private StringBuilder extendFirst(StringBuilder actual) {
        shift--;
        return new StringBuilder(".").append(actual);
    }

    private StringBuilder spread(StringBuilder input) {
        StringBuilder output = new StringBuilder("..");
        int length = input.length();
        for (int i = 0; i < length - 4; i++) {
            String pattern;
            pattern = input.substring(i, i + 5);
            output.append(characterToPattern(pattern));
        }
        return output;
    }

    private char characterToPattern(String pattern) {
        if (rules.containsKey(pattern)) {
            return rules.get(pattern);
        }
        return '.';
    }

    private void processinput(List<String> input) {
        initialState = getInitialStateFrom(input.get(0));
        for (int i = 2; i < input.size(); i++) {
            buildRule(input.get(i));
        }
    }

    private void buildRule(String line) {
        String[] words = line.split(" => ");
        rules.put(words[0], words[1].charAt(0));
    }

    private String getInitialStateFrom(String line) {
        int indexOfLastSpace = line.lastIndexOf(' ');
        return line.substring(indexOfLastSpace + 1);
    }

    public Map<String, Character> getRules() {
        return rules;
    }
}
