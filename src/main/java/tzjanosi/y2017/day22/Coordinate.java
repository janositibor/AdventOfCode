package tzjanosi.y2017.day22;

import java.util.Objects;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate(Coordinate original) {
        this(original.x, original.y);
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate move(Coordinate direction) {
        return new Coordinate(x + direction.x, y + direction.y);
    }

    public Coordinate right() {
        return new Coordinate(-1 * y, x);
    }

    public Coordinate left() {
        return new Coordinate(y, -1 * x);
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
