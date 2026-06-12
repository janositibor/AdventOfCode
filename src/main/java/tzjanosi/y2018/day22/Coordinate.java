package tzjanosi.y2018.day22;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int manhattanDistanceTo(Coordinate other) {
        return Math.abs(x - other.x) + Math.abs(y - other.y);
    }

    public List<Coordinate> findNeighbours(Coordinate limit) {
        List<Coordinate> output = new ArrayList<>();
        for (int i = -1; i <= 1; i += 2) {
            Coordinate coordinate = new Coordinate(x + i, y);
            if (coordinate.withinRange(limit)) {
                output.add(coordinate);
            }
            coordinate = new Coordinate(x, y + i);
            if (coordinate.withinRange(limit)) {
                output.add(coordinate);
            }
        }
        return output;
    }

    public boolean withinRange(Coordinate limit) {
        if (x < 0 || y < 0) {
            return false;
        }
        return x <= limit.x && y <= limit.y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coordinate that = (Coordinate) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
