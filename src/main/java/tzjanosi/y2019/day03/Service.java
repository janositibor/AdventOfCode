package tzjanosi.y2019.day03;

import java.util.*;

public class Service {
    private List<String> instructions1 = new ArrayList<>();
    private List<String> instructions2 = new ArrayList<>();
    private Set<Coordinate> route1 = new HashSet<>();
    private Set<Coordinate> route2 = new HashSet<>();

    public Service(List<String> input) {
        processInput(input);
    }

    public int findNearestCrossing() {
        createPaths();
        return route1.stream()
                .filter(route2::contains)
                .mapToInt(Coordinate::manhattanDistance)
                .min()
                .orElseThrow(() -> new IllegalStateException("No crossing found"));
    }

    private void createPaths() {
        createPath(route1, instructions1);
        createPath(route2, instructions2);
    }

    private void createPath(Set<Coordinate> path, List<String> instructions) {
        Coordinate actual = new Coordinate(0, 0);
        for (int i = 0; i < instructions.size(); i++) {
            String instruction = instructions.get(i);
            Coordinate direction = findDirection(instruction.charAt(0));
            int length = Integer.parseInt(instruction.substring(1));
            for (int j = 1; j <= length; j++) {
                actual = actual.shift(direction);
                path.add(actual);
            }
        }
    }

    private Coordinate findDirection(char direction) {
        switch (direction) {
            case 'U':
                return new Coordinate(0, 1);
            case 'D':
                return new Coordinate(0, -1);
            case 'R':
                return new Coordinate(1, 0);
            case 'L':
                return new Coordinate(-1, 0);
            default:
                throw new IllegalStateException("Unexpected direction: " + direction);
        }
    }

    private void processInput(List<String> input) {
        instructions1 = processLine(input.get(0));
        instructions2 = processLine(input.get(1));
    }

    private List<String> processLine(String line) {
        String[] words = line.split(",");
        return Arrays.asList(words);
    }
}
