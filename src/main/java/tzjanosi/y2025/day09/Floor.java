package tzjanosi.y2025.day09;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    private List<Coordinate> coordinates = new ArrayList<>();
    private List<Segment> sides = new ArrayList<>();
    private long maxArea;

    public Floor(List<String> input) {
        processInput(input);
    }

    public long findMaxAreaInside() {
        for (int i = 0; i < coordinates.size() - 1; i++) {
            Coordinate from = coordinates.get(i);
            for (int j = i + 1; j < coordinates.size(); j++) {
                Coordinate to = coordinates.get(j);
                Rectangle rectangle = new Rectangle(from, to);
                if (rectangle.isInside(sides)) {
                    long area = from.areaWith(to);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }

    public long findMaxArea() {
        for (int i = 0; i < coordinates.size() - 1; i++) {
            Coordinate from = coordinates.get(i);
            for (int j = i + 1; j < coordinates.size(); j++) {
                Coordinate to = coordinates.get(j);
                long area = from.areaWith(to);
                maxArea = Math.max(maxArea, area);
            }
        }
        return maxArea;
    }

    private void processInput(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            processLine(input.get(i));
        }
        buildSides();
    }

    private void buildSides() {
        Segment side;
        for (int i = 0; i < coordinates.size(); i++) {
            int next;
            if (i == coordinates.size() - 1) {
                next = 0;
            } else {
                next = i + 1;
            }
            side = new Segment(coordinates.get(i), coordinates.get(next));
            sides.add(side);
        }
    }

    private void processLine(String line) {
        String[] numbersAsString = line.split(",");
        Coordinate coordinate = new Coordinate(Integer.parseInt(numbersAsString[0]), Integer.parseInt(numbersAsString[1]));
        coordinates.add(coordinate);
    }
}
