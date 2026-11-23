package tzjanosi.y2019.day10;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Service {
    private List<Coordinate> asteroids = new ArrayList<>();
    private Coordinate watchTower;

    public Service(List<String> input) {
        processInput(input);
    }

    public int findTheAsteroidInSequence(int order) {
        findWatchTower();
        Map<ViewDirection, NavigableSet<ViewDirection>> map = orderAsteroidsFromWatchtower();
        List<ViewDirection> orderAsteroids = orderAsteroidList(map);
        ViewDirection actualView = orderAsteroids.get(order - 1);
        Coordinate asteroid = new Coordinate(watchTower);
        asteroid.shift(actualView.getOriginal());
        return asteroid.getCoordinateID();
    }

    private List<ViewDirection> orderAsteroidList(Map<ViewDirection, NavigableSet<ViewDirection>> map) {
        return map.values().stream().map(NavigableSet::first).sorted(Comparator.comparing(ViewDirection::getAngle)).toList();
    }

    @SuppressWarnings({"PMD.UnnecessaryLocalBeforeReturn"})
    private Map<ViewDirection, NavigableSet<ViewDirection>> orderAsteroidsFromWatchtower() {
        List<ViewDirection> others = asteroids.stream()
                .filter(c -> !c.equals(watchTower))
                .map(c -> new ViewDirection(watchTower.difference(c)))
                .toList();
        Map<ViewDirection, NavigableSet<ViewDirection>> map = others.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.toCollection(TreeSet::new)
                ));
        return map;
    }

    public int watchTowerCoordinateID() {
        findWatchTower();
        return watchTower.getCoordinateID();
    }

    private void findWatchTower() {
        Map<Coordinate, Integer> mapAsteroidsAnVisibility = asteroids.stream().collect(Collectors.toMap(c -> c, this::calculateNumberOfVisibleAsteroidsFromWatchtower));
        watchTower = mapAsteroidsAnVisibility.entrySet().stream()
                .sorted(Comparator.comparingInt(Map.Entry<Coordinate, Integer>::getValue).reversed())
                .findFirst()
                .map(Map.Entry::getKey)
                .get();
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
