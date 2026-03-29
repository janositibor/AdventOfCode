package tzjanosi.y2018.day11;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Coordinate implements Comparable<Coordinate> {

    private int x;
    private int y;
    private int value;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
        value = 300 * y + x;
    }

    public Coordinate(Coordinate original) {
        x = original.x;
        y = original.y;
    }

    @Override
    public int compareTo(Coordinate other) {
        return value - other.value;
    }

    public Set<Coordinate> getNearByCoordinates() {
        Set<Coordinate> output = new HashSet<>();
        for (int i = -1; i <= 1; i++) {
            int actualX = x + i;
            for (int j = -1; j <= 1; j++) {
                int actualY = y + j;
                output.add(new Coordinate(actualX, actualY));
            }

        }
        return output;
    }

    public boolean isNearBy(Coordinate other) {
        return Math.abs(x - other.x) <= 1 && Math.abs(y - other.y) <= 1;
    }

    public Coordinate add(Coordinate shift) {
        return new Coordinate(x + shift.x, y + shift.y);
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coordinate that = (Coordinate) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getValue() {
        return value;
    }
}
