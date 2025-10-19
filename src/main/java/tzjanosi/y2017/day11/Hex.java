package tzjanosi.y2017.day11;

import java.util.ArrayList;
import java.util.List;

public class Hex {
    private Coordinate location = new Coordinate(0, 0);
    private List<Direction> directions = new ArrayList<>();

    public Hex(String input) {
        processInput(input);
    }

    private void processInput(String input) {
        String[] words = input.split(",");
        for (int i = 0; i < words.length; i++) {
            addDirection(words[i]);
        }
    }

    private void addDirection(String word) {
        for (Direction direction : Direction.values()) {
            if (direction.getSign().equals(word)) {
                directions.add(direction);
            }
        }
    }

    public int move() {
        for (int i = 0; i < directions.size(); i++) {
            Direction direction = directions.get(i);
            location.shift(direction.getShift());
        }
        return calculateStepsBackToOrigin();
    }

    private int calculateStepsBackToOrigin() {
        double x = Math.abs(location.getX());
        double y = Math.abs(location.getY());
        double xStep = Direction.NORTH_EAST.getShift().getX();
        double yStep = Direction.NORTH_EAST.getShift().getY();
        return (int) Math.round((x / xStep) + Math.abs(y - (x / xStep) * yStep));
    }
}
