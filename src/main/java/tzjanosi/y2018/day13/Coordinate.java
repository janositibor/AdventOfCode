package tzjanosi.y2018.day13;

import java.util.Objects;

public class Coordinate implements Comparable<Coordinate> {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String stringRepresentation() {
        return x + "," + y;
    }

    public boolean isHorizontal() {
        return y == 0;
    }

    public boolean isVertical() {
        return x == 0;
    }

    public Coordinate add(Coordinate other) {
        return new Coordinate(x + other.x, y + other.y);
    }

    public Coordinate straight() {
        return new Coordinate(x, y);
    }

    public Coordinate rotateClockWise() {
        return new Coordinate(-y, x);
    }

    public Coordinate rotateCounterClockWise() {
        return new Coordinate(y, -x);
    }

    @Override
    public int compareTo(Coordinate other) {
        if (x == other.x) {
            return y - other.y;
        } else {
            return x - other.x;
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
