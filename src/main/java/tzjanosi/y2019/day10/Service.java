package tzjanosi.y2019.day10;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Service {
    private List<Coordinate> asteroids = new ArrayList<>();

    public Service(List<String> input) {
        processInput(input);
    }

    public int calculateNumberOfVisibleAsteroids() {
        return asteroids.stream()
                .mapToInt(this::calculateNumberOfVisibleAsteroidsFromWatchtower)
                .max()
                .orElseThrow(() -> new IllegalStateException("Empty asteroids list!"));
    }

    private int calculateNumberOfVisibleAsteroidsFromWatchtower(Coordinate watchTower) {
        List<ViewDirection> others = asteroids.stream()
                .filter(c -> !c.equals(watchTower))
                .map(c -> new ViewDirection(watchTower.difference(c)))
                .toList();
        Map<ViewDirection, Long> map = others.stream().collect(Collectors.groupingBy(vD -> vD, Collectors.counting()));
        return map.keySet().size();
    }

    private void processInput(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            processLine(i, line);
        }
    }

    private void processLine(int i, String line) {
        for (int j = 0; j < line.length(); j++) {
            if (line.charAt(j) == '#') {
                Coordinate coordinate = new Coordinate(j, i);
                asteroids.add(coordinate);
            }
        }
    }
}
