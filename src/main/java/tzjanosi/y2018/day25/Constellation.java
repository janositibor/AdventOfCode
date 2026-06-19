package tzjanosi.y2018.day25;

import java.util.HashSet;
import java.util.Set;

public class Constellation {
    private Set<Coordinate> coordinates;

    public Constellation(Coordinate coordinate) {
        coordinates = new HashSet<>();
        coordinates.add(coordinate);
    }

    public boolean closeTo(Constellation other) {
        return coordinates.stream()
                .anyMatch(basicCoordinate ->
                        other.coordinates.stream().
                                anyMatch(otherCoordinate -> otherCoordinate.isCloseEnough(basicCoordinate)));

    }

    public void merge(Constellation other) {
        for (Coordinate coordinate : other.coordinates) {
            coordinates.add(coordinate);
        }
    }
}
