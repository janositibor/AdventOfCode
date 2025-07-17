package tzjanosi.y2016.day02;

import java.util.ArrayList;
import java.util.List;

public class KeyPad {
    private Coordinate position = new Coordinate(0, 0);
    private List<List<Direction>> instructions;
    private StringBuilder output = new StringBuilder();

    public KeyPad(List<List<Direction>> instructions) {
        this.instructions = new ArrayList<>(instructions);
    }

    private int calculateNumberToPosition() {
        Coordinate positionForCalculation = position.add(new Coordinate(1, 1));
        return 3 * positionForCalculation.getY() + positionForCalculation.getX() + 1;
    }

    private void step(Direction direction) {
        Coordinate newPosition = position.add(direction.getDirection());
        if (isValid(newPosition)) {
            position = newPosition;
        }
    }

    private boolean isValid(Coordinate coordinate) {
        return Math.abs(coordinate.getX()) <= 1 && Math.abs(coordinate.getY()) <= 1;
    }

    public void calculateOutput() {
        reset();
        for (int i = 0; i < instructions.size(); i++) {
            List<Direction> line = instructions.get(i);
            for (int j = 0; j < line.size(); j++) {
                step(line.get(j));
            }
            output.append(calculateNumberToPosition());
        }
    }

    private void reset() {
        position = new Coordinate(0, 0);
        output = new StringBuilder();
    }

    public StringBuilder getOutput() {
        return output;
    }
}
