package tzjanosi.y2018.day03;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Fabric {
    private List<Rectangle> rectangles = new ArrayList<>();
    private Map<Coordinate, Integer> claims = new TreeMap<>(); // NOPMD UseConcurrentHashMap

    public Fabric(List<String> input) {
        processInput(input);
        buildClaims();
    }

    public int areaOfConflictingClaims() {
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
        List<Coordinate> involvedCoordinates = rectangle.involvedCoordinates();
        for (int i = 0; i < involvedCoordinates.size(); i++) {
            Coordinate coordinate = involvedCoordinates.get(i);
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
        Coordinate start = getStartCoordinate(parts[2]);
        Coordinate shift = getShiftCoordinate(parts[3]);
        rectangles.add(new Rectangle(start, start.move(shift)));
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
