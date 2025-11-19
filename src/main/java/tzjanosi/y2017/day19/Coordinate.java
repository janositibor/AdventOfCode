package tzjanosi.y2017.day19;

import java.util.Objects;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Coordinate other) {
        return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
    }

    public boolean isSameDirection(Coordinate other) {
        boolean sameColumn = ((x == 0) && (x == other.x) && (y * other.y > 0));
        boolean sameRow = ((y == 0) && (y == other.y) && (x * other.x > 0));
        return sameColumn || sameRow;
    }

    public Coordinate minus(Coordinate other) {
        return new Coordinate(x - other.x, y - other.y);
    }

    public boolean isNeighbour(Coordinate other) {
        boolean xNeighbour = (Math.abs(x - other.x) == 1 && y == other.y);
        boolean yNeighbour = (Math.abs(y - other.y) == 1 && x == other.x);
        return xNeighbour || yNeighbour;
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

    public boolean between(Coordinate from, Coordinate to) {
        Coordinate direction1 = this.minus(from);
        Coordinate direction2 = to.minus(from);
        return direction1.isSameDirection(direction2) && (from.distance(this) < from.distance(to));
    }
}