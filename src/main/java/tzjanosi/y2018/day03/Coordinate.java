package tzjanosi.y2018.day03;

import java.util.Objects;

public class Coordinate implements Comparable<Coordinate> {
    private int x;
    private int y;

    public Coordinate(Coordinate original) {
        this(original.x, original.y);
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Coordinate o) {
        if (this.x != o.x) {
            return Integer.compare(this.x, o.x);
        }
        return Integer.compare(this.y, o.y);
    }

    public Coordinate move(Coordinate direction) {
        return new Coordinate(x + direction.x, y + direction.y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
