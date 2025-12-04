package tzjanosi.y2025.day04;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Storage {
    private Set<Coordinate> rolls = new HashSet<>();

    public Storage(List<String> input) {
        processInput(input);
    }

    public int numberOfAllRemovableRolls() {
        int totalNumberOfRolls = rolls.size();
        Set<Coordinate> rollsToRemove;
        do {
            rollsToRemove = accessibleRolls();
            if (!rollsToRemove.isEmpty()) {
                rolls.removeAll(rollsToRemove);
            }
        } while (!rollsToRemove.isEmpty());
        return totalNumberOfRolls - rolls.size();
    }

    private Set<Coordinate> accessibleRolls() {
        return rolls.stream()
                .filter(r -> rolls.stream().filter(r2 -> r2.neighbour(r)).count() < 4)
                .collect(Collectors.toSet());

    }

    public int numberOfAccessibleRolls() {
        return (int) rolls.stream()
                .filter(r -> rolls.stream().filter(r2 -> r2.neighbour(r)).count() < 4)
                .count();
    }

    private void processInput(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            processLine(i, input.get(i));
        }
    }

    private void processLine(int y, String line) {
        char[] chars = line.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '@') {
                rolls.add(new Coordinate(i, y));
            }
        }
    }
}
