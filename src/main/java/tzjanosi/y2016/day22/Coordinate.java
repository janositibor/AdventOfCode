package tzjanosi.y2016.day22;

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

    public List<Coordinate> getNeighbours(Coordinate limit) {
        List<Coordinate> output = new ArrayList<>();
        List<Integer> xs = new ArrayList<>();
        List<Integer> ys = new ArrayList<>();
        if (x > 0) {
            xs.add(x - 1);
        }
        if (x < limit.x) {
            xs.add(x + 1);
        }
        if (y > 0) {
            ys.add(y - 1);
        }
        if (y < limit.y) {
            ys.add(y + 1);
        }
        for (int i = 0; i < xs.size(); i++) {
            output.add(new Coordinate(xs.get(i), y));
        }
        for (int i = 0; i < ys.size(); i++) {
            output.add(new Coordinate(x, ys.get(i)));
        }
        return output;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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
