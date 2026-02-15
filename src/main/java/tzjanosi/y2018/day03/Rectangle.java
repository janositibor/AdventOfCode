package tzjanosi.y2018.day03;

import java.util.*;

public class Rectangle {
    private int id;
    private Coordinate from;
    private Coordinate to;

    public Rectangle(int id, Coordinate from, Coordinate to) {
        this.id = id;
        this.from = from;
        this.to = to;
    }

    public Set<Coordinate> involvedCoordinates() {
        Set<Coordinate> output = new TreeSet<>();
        for (int i = from.getX(); i <= to.getX(); i++) {
            for (int j = from.getY(); j <= to.getY(); j++) {
                output.add(new Coordinate(i, j));
            }
        }
        return output;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Rectangle rectangle = (Rectangle) o;
        return id == rectangle.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "id=" + id +
                ", from=" + from +
                ", to=" + to +
                '}';
    }
}
