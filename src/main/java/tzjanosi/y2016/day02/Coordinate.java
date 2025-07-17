package tzjanosi.y2016.day02;

import java.util.Objects;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate multiply(int m) {
        return new Coordinate(m * x, m * y);
    }

    public Coordinate unitDirection() {
        int length = intLength();
        return new Coordinate(x / length, y / length);
    }

    public Coordinate add(Coordinate shift) {
        return new Coordinate(x + shift.x, y + shift.y);
    }

    public Coordinate minus(Coordinate from) {
        return new Coordinate(x - from.x, y - from.y);
    }

    public int intLength() {
        return Math.abs(x) + Math.abs(y);
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
