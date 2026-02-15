package tzjanosi.y2018.day03;

import java.util.*;

public class Fabric {
    private List<Rectangle> rectangles = new ArrayList<>();
    private Map<Coordinate, Integer> claims = new TreeMap<>(); // NOPMD UseConcurrentHashMap
    private Set<Rectangle> candidates;
    private Set<Coordinate> occupiedCoordinates = new TreeSet<>();


    public Fabric(List<String> input) {
        processInput(input);
    }

    public int idOfTheNonOverlappingRectangle() {
        candidates = new HashSet<>(rectangles);
        for (int i = 0; i < rectangles.size(); i++) {
            boolean result = isNonOverlappingRectangle(i);
            if (result) {
                return rectangles.get(i).getId();
            }
        }
        throw new IllegalStateException("No non overlapping rectangle found!");
    }

    private boolean isNonOverlappingRectangle(int order) {
        Rectangle rectangle = rectangles.get(order);
        Set<Coordinate> involvedCoordinates = rectangle.involvedCoordinates();
        if (isOverlapping(involvedCoordinates, occupiedCoordinates)) {
            occupiedCoordinates.addAll(involvedCoordinates);
            candidates.remove(rectangle);
        } else {
            for (int j = order + 1; j < rectangles.size() && candidates.contains(rectangle); j++) {
                Rectangle rectangle2 = rectangles.get(j);
                if (candidates.contains(rectangle2)) {
                    Set<Coordinate> involvedCoordinates2 = rectangle2.involvedCoordinates();
                    if (isOverlapping(involvedCoordinates, involvedCoordinates2)) {
                        occupiedCoordinates.addAll(involvedCoordinates);
                        occupiedCoordinates.addAll(involvedCoordinates2);
                        candidates.remove(rectangle);
                        candidates.remove(rectangle2);
                    }
                }
            }
        }
        return candidates.contains(rectangle);
    }

    private boolean isOverlapping(Set<Coordinate> set1, Set<Coordinate> set2) {
        return set1.stream().anyMatch(set2::contains);
    }

    public int areaOfConflictingClaims() {
        buildClaims();
        return (int) claims.values().stream()
                .filter(d -> d > 1)
                .count();
    }

    private void buildClaims() {
        for (int i = 0; i < rectangles.size(); i++) {
            claimsFromRectangle(rectangles.get(i));
        }
    }

    private void claimsFromRectangle(Rectangle rectangle) {
        Set<Coordinate> involvedCoordinates = rectangle.involvedCoordinates();
        for (Coordinate coordinate : involvedCoordinates) {
            addCoordinateToClaims(coordinate);
        }
    }

    private void addCoordinateToClaims(Coordinate coordinate) {
        if (!claims.containsKey(coordinate)) {
            claims.put(coordinate, 0);
        }
        int actualValue = claims.get(coordinate);
        actualValue++;
        claims.put(coordinate, actualValue);
    }

    private void processInput(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            processLine(line);
        }
    }

    private void processLine(String line) {
        String[] parts = line.split(" ");
        int id = getIDFrom(parts[0]);
        Coordinate start = getStartCoordinate(parts[2]);
        Coordinate shift = getShiftCoordinate(parts[3]);
        rectangles.add(new Rectangle(id, start, start.move(shift)));
    }

    private int getIDFrom(String part) {
        return Integer.parseInt(part.substring(1));
    }

    private Coordinate getShiftCoordinate(String word) {
        String[] parts = word.split("x");
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        return new Coordinate(x - 1, y - 1);
    }

    private Coordinate getStartCoordinate(String word) {
        String[] parts = word.split(",");
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1].substring(0, parts[1].length() - 1));
        return new Coordinate(x, y);
    }
}
