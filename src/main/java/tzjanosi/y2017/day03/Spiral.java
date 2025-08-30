package tzjanosi.y2017.day03;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Spiral {
    private Map<Coordinate, Integer> slots = new ConcurrentHashMap<>();

    private int calculateSumOfNeighbours(Coordinate actual) {
        List<Coordinate> neighbours = actual.getNeighbours();

        return slots.entrySet()
                .stream()
                .filter(s -> neighbours.contains(s.getKey()))
                .mapToInt(Map.Entry::getValue)
                .sum();
    }

    public int stretch(int limit) {
        int value = 1;
        slots.put(new Coordinate(0, 0), value);
        int counter = 2;
        while (value <= limit) {
            Coordinate coordinate = getCoordinate(counter);
            value = calculateSumOfNeighbours(coordinate);
            slots.put(coordinate, value);
            counter++;
        }
        return value;
    }

    public int getDistance(int order) {
        Coordinate coordinate = getCoordinate(order);
        return coordinate.getManhattanDistanceFromOrigo();
    }

    private Coordinate getCoordinate(int order) {
        if (order == 1) {
            return new Coordinate(0, 0);
        }
        int insideSquareSideLength = getInsideSquareSideLength(order);
        int remain = (int) (order - Math.pow(insideSquareSideLength, 2));

        return calculateCoordinate(remain, insideSquareSideLength);
    }

    private Coordinate calculateCoordinate(int input, int insideSquareSideLength) {
        int remain = input;
        int start = insideSquareSideLength / 2;
        Coordinate coordinate = new Coordinate(start + 1, start);
        remain--;
        if (remain <= insideSquareSideLength) {
            return coordinate.shift(new Coordinate(0, -1 * remain));
        }
        coordinate = coordinate.shift(new Coordinate(0, -1 * (insideSquareSideLength)));
        remain -= insideSquareSideLength;

        if (remain <= insideSquareSideLength + 1) {
            return coordinate.shift(new Coordinate(-1 * remain, 0));
        }
        coordinate = coordinate.shift(new Coordinate(-1 * (insideSquareSideLength + 1), 0));
        remain -= (insideSquareSideLength + 1);

        if (remain <= insideSquareSideLength + 1) {
            return coordinate.shift(new Coordinate(0, remain));
        }
        coordinate = coordinate.shift(new Coordinate(0, 1 * (insideSquareSideLength + 1)));
        remain -= (insideSquareSideLength + 1);

        coordinate = coordinate.shift(new Coordinate(1 * remain, 0));

        return coordinate;
    }

    private int getInsideSquareSideLength(int order) {
        int index = -1;
        do {
            index += 2;
        }
        while (Math.pow(index, 2) < order);
        return index - 2;
    }
}
