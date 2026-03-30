package tzjanosi.y2018.day11;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Board {

    private Map<Coordinate, Element> elements = new ConcurrentHashMap<>();
    private int limit = 300;
    private int[][] prefix = new int[limit + 1][limit + 1];

    public Board(int gridSerialNumber) {
        buildElements(gridSerialNumber);
    }

    public String findExtendedMaxArea() {
        buildPrefix();
        return maxFromPrefix();
    }

    private String maxFromPrefix() {
        int maxSum = Integer.MIN_VALUE;
        String output = "";

        for (int size = 1; size <= limit; size++) {
            for (int i = 0; i <= limit - size; i++) {
                for (int j = 0; j <= limit - size; j++) {
                    int actual = sum(i, j, size);
                    if (actual > maxSum) {
                        maxSum = actual;
                        output = String.format("%d,%d,%d", i + 1, j + 1, size);
                    }
                }
            }
        }
        return output;
    }

    private int sum(int fromX, int fromY, int size) {
        int toX = fromX + size;
        int toY = fromY + size;

        return prefix[toX][toY]
                - prefix[fromX][toY]
                - prefix[toX][fromY]
                + prefix[fromX][fromY];
    }

    private void buildPrefix() {
        for (int i = 1; i <= limit; i++) {
            for (int j = 1; j <= limit; j++) {
                prefix[i][j] = elements.get(new Coordinate(i, j)).getPower()
                        + prefix[i - 1][j]
                        + prefix[i][j - 1]
                        - prefix[i - 1][j - 1];
            }
        }
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
