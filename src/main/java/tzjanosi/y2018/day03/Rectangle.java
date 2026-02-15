package tzjanosi.y2018.day03;

import java.util.ArrayList;
import java.util.List;

public class Rectangle {
    private Coordinate from;
    private Coordinate to;

    public Rectangle(Coordinate from, Coordinate to) {
        this.from = from;
        this.to = to;
    }

    public List<Coordinate> involvedCoordinates() {
        List<Coordinate> output = new ArrayList<>();
        for (int i = from.getX(); i <= to.getX(); i++) {
            for (int j = from.getY(); j <= to.getY(); j++) {
                output.add(new Coordinate(i, j));
            }
        }
        return output;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}
