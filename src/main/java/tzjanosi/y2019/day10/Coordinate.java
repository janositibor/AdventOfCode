package tzjanosi.y2019.day10;

import java.util.Objects;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate(Coordinate original) {
        x = original.x;
        y = original.y;
    }

    public void divideBy(int divider) {
        x = x / divider;
        y = y / divider;
    }

    public void shift(Coordinate shift) {
        x += shift.x;
        y += shift.y;
    }

    public Coordinate difference(Coordinate other) {
        return new Coordinate(other.x - x, other.y - y);
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public int getCoordinateID() {
        return 100 * x + y;
    }
}
