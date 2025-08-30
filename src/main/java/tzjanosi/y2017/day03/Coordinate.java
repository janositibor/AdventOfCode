package tzjanosi.y2017.day03;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate shift(Coordinate original) {
        return new Coordinate(x + original.x, y + original.y);
    }

    public int getManhattanDistanceFromOrigo() {
        return Math.abs(x) + Math.abs(y);
    }

    public List<Coordinate> getNeighbours() {
        List<Coordinate> output = new ArrayList<>();
        int[] differences = {-1, 0, 1};
        for (int i = 0; i < differences.length; i++) {
            for (int j = 0; j < differences.length; j++) {
                if (i != 1 || j != 1) {
                    Coordinate shift = new Coordinate(differences[i], differences[j]);
                    output.add(shift(shift));
                }
            }
        }
        return output;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
