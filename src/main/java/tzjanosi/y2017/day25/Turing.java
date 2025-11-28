package tzjanosi.y2017.day25;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Turing {
    private Set<State> states = new HashSet<>();
    private char actualStateAsChar;
    private State actualState;
    private int stepCount;
    private int cursor;
    private Map<Integer, Integer> tape = new ConcurrentHashMap<>();


    public Turing(List<String> input) {
        processInput(input);
        actualState = findActualState();
    }

    private State findActualState() {
        return states.stream()
                .filter(s -> s.getName() == actualStateAsChar)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No state found: " + actualStateAsChar));

    }

    public int exec() {
        for (int i = 0; i < stepCount; i++) {
            int actualValue = getActualValue();
            updatedValue(actualValue);
            cursor += actualState.nextStepDirection(actualValue);
            actualStateAsChar = actualState.nextState(actualValue);
            actualState = findActualState();
        }
        return tape.size();
    }

    private void updatedValue(int actualValue) {
        int updatedValue = actualState.nextValue(actualValue);
        if (updatedValue != actualValue) {
            if (updatedValue == 1) {
                tape.put(cursor, updatedValue);
            } else {
                tape.remove(cursor);
            }
        }
    }

    private int getActualValue() {
        return tape.containsKey(cursor) ? tape.get(cursor) : 0;
    }

    private void processInput(List<String> input) {
        actualStateAsChar = extractChar(input.get(0));
        stepCount = extractInt(input.get(1), 1);
        for (int i = 3; i < input.size(); i += 10) {
            char name = extractChar(input.get(i));
            RuleParameters ruleparameters = buildRuleParametersFrom(new ArrayList<>(input.subList(i + 2, i + 9)));
            State actual = new State(name, ruleparameters);
            states.add(actual);
        }
    }

    private RuleParameters buildRuleParametersFrom(List<String> block) {
        int valueFrom0 = extractInt(block.get(0), 0);
        int stepDirectionFrom0 = extractDirection(block.get(1));
        char nextStateFrom0 = extractChar(block.get(2));
        int valueFrom1 = extractInt(block.get(4), 0);
        int stepDirectionFrom1 = extractDirection(block.get(5));
        char nextStateFrom1 = extractChar(block.get(6));
        return new RuleParameters(valueFrom0, stepDirectionFrom0, nextStateFrom0, valueFrom1, stepDirectionFrom1, nextStateFrom1);
    }

    private int extractDirection(String line) {
        String[] words = line.split("[ .]");
        return "right".equals(words[words.length - 1]) ? 1 : -1;
    }

    private int extractInt(String line, int shift) {
        String[] words = line.split("[ .:]");
        return Integer.parseInt(words[words.length - 1 - shift]);
    }

    private char extractChar(String line) {
        String[] words = line.split("[ .:]");
        return words[words.length - 1].toCharArray()[0];
    }

    @Override
    public String toString() {
        return "Turing{" +
                "states=" + states +
                ", startingState=" + actualStateAsChar +
                ", stepCount=" + stepCount +
                '}';
    }
}
