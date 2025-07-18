package tzjanosi.y2016.day02;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class KeyPadPart2 {

    private Coordinate position = new Coordinate(-2, 0);
    private List<List<Direction>> instructions;
    private StringBuilder output = new StringBuilder();
    private Map<Coordinate, String> numberValues = new ConcurrentHashMap<>();

    public KeyPadPart2(List<List<Direction>> instructions) {
        this.instructions = new ArrayList<>(instructions);
        fillNumberValues();
    }

    private void fillNumberValues() {
        numberValues.put(new Coordinate(0, -2), "1");
        numberValues.put(new Coordinate(-1, -1), "2");
        numberValues.put(new Coordinate(0, -1), "3");
        numberValues.put(new Coordinate(1, -1), "4");
        numberValues.put(new Coordinate(-2, 0), "5");
        numberValues.put(new Coordinate(-1, 0), "6");
        numberValues.put(new Coordinate(-0, 0), "7");
        numberValues.put(new Coordinate(1, 0), "8");
        numberValues.put(new Coordinate(2, 0), "9");
        numberValues.put(new Coordinate(-1, 1), "A");
        numberValues.put(new Coordinate(0, 1), "B");
        numberValues.put(new Coordinate(1, 1), "C");
        numberValues.put(new Coordinate(0, 2), "D");
    }


    private String calculateNumberToPosition() {
        return numberValues.get(position);
    }

    private void step(Direction direction) {
        Coordinate newPosition = position.add(direction.getDirection());
        if (isValid(newPosition)) {
            position = newPosition;
        }
    }

    private boolean isValid(Coordinate coordinate) {
        return coordinate.intLength() <= 2;
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
        position = new Coordinate(-2, 0);
        output = new StringBuilder();
    }

    public StringBuilder getOutput() {
        return output;
    }
}
