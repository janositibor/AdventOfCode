package tzjanosi.y2017.day19;

import java.util.Optional;
import java.util.Set;

public class Node {
    private Coordinate coordinate;
    private Coordinate incomingDirection;

    public Node(Coordinate coordinate, Coordinate incomingDirection) {
        this.coordinate = coordinate;
        this.incomingDirection = incomingDirection;
    }

    public Optional<Coordinate> findOutGoingDirection(Set<Sign> signs) {
        return signs.stream()
                .filter(s -> s.getValue() != ' ')
                .filter(s -> s.getCoordinate().isNeighbour(coordinate))
                .filter(s -> !s.getCoordinate().equals(coordinate.minus(incomingDirection)))
                .map(s -> s.getCoordinate().minus(coordinate))
                .findFirst()
                ;
    }
}
