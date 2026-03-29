package tzjanosi.y2018.day11;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Board {

    private Map<Coordinate, Element> elements = new ConcurrentHashMap<>();
    private int limit = 300;

    public Board(int gridSerialNumber) {
        buildElements(gridSerialNumber);
    }

    public Coordinate findMaxArea() {
        Map<Coordinate, Optional<Integer>> tempMap = elements.entrySet().stream()
                .filter((Map.Entry<Coordinate, Element> e) -> isInner(e.getKey()))
                .map(Map.Entry::getValue)
                .collect(Collectors.groupingBy(Element::getCoordinate, Collectors.mapping(this::calculateAreaPower, Collectors.maxBy(Comparator.naturalOrder()))));

        Coordinate center = tempMap.entrySet().stream()
                .filter(e -> e.getValue().isPresent())
                .max(Comparator.comparingInt(e -> e.getValue().get()))
                .get()
                .getKey();
        return center.add(new Coordinate(-1, -1));
    }

    private int calculateAreaPower(Element actual) {
        Set<Coordinate> nearByCoordinates = actual.getCoordinate().getNearByCoordinates();
        return nearByCoordinates.stream()
                .map(elements::get)
                .mapToInt(Element::getPower)
                .sum();
    }

    private boolean isInner(Coordinate coordinate) {
        return isInnerCoordinate(coordinate.getX()) && isInnerCoordinate(coordinate.getY());
    }

    private boolean isInnerCoordinate(int input) {
        return 1 < input && input < limit;
    }

    private void buildElements(int gridSerialNumber) {
        for (int i = 1; i <= limit; i++) {
            for (int j = 1; j <= limit; j++) {
                Coordinate coordinate = new Coordinate(i, j);
                Element element = new Element(coordinate, gridSerialNumber);
                elements.put(coordinate, element);
            }
        }
    }

    public Map<Coordinate, Element> getElements() {
        return elements;
    }
}
