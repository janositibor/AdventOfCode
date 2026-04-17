package tzjanosi.y2018.day15;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Coordinate implements Comparable<Coordinate> {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public List<Coordinate> getNeighbours() {
        List<Coordinate> output = new ArrayList<>();
        output.add(new Coordinate(x, y - 1));
        output.add(new Coordinate(x - 1, y));
        output.add(new Coordinate(x + 1, y));
        output.add(new Coordinate(x, y + 1));
        return output;
    }

    public String stringRepresentation() {
        return x + "," + y;
    }

    public Coordinate add(Coordinate other) {
        return new Coordinate(x + other.x, y + other.y);
    }

    @Override
    public int compareTo(Coordinate other) {
        if (y == other.y) {
            return x - other.x;
        } else {
            return y - other.y;
        }
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

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
