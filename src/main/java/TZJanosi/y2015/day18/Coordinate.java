package TZJanosi.y2015.day18;

import java.util.Objects;

//public class Coordinate implements Comparable<Coordinate> {
public class Coordinate {
    private int x;
    private int y;
//    private int limit;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isInTheArea(Coordinate from, Coordinate to) {
        if (x < from.x || to.x < x) {
            return false;
        }
        if (y < from.y || to.y < y) {
            return false;
        }
        return true;
    }

//    @Override
//    public int compareTo(Coordinate o) {
//        return (limit * (y - o.y) + (x - o.x));
//    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

//    public void setLimit(int limit) {
//        this.limit = limit;
//    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

//    public int getLimit() {
//        return limit;
//    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
