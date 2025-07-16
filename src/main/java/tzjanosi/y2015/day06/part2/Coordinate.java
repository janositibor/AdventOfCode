package tzjanosi.y2015.day06.part2;

import java.util.Objects;

public class Coordinate implements Comparable<Coordinate> {
    private int x;
    private int y;
    private int limit;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Coordinate o) {
        return (limit * (y - o.y) + (x - o.x));
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

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLimit() {
        return limit;
    }
}
