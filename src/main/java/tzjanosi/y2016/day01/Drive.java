package tzjanosi.y2016.day01;

import java.util.ArrayList;
import java.util.List;

public class Drive {
    private Taxi taxi = new Taxi();
    private List<Instruction> instructions = new ArrayList<>();
    private List<Coordinate> route = new ArrayList<>();

    public Drive(String input) {
        fillInstructions(input);
        route.add(new Coordinate(0, 0));
    }

    public int process() {
        for (int i = 0; i < instructions.size(); i++) {
            taxi.move(instructions.get(i));
        }
        return taxi.calculateDistance();
    }

    public int processPart2() {
        for (int i = 0; i < instructions.size(); i++) {
            taxi.move(instructions.get(i));
            Coordinate newLocation = taxi.getLocation();
            if (isCrossing(newLocation)) {
                break;
            }
        }
        return route.getLast().intLength();
    }

    private boolean isCrossing(Coordinate end) {
        Coordinate start = route.getLast();
        Coordinate direction = end.minus(start);
        int length = direction.intLength();
        Coordinate unitDirection = direction.unitDirection();
        for (int i = 1; i <= length; i++) {
            Coordinate newCoordinate = start.add(unitDirection.multiply(i));
            route.add(newCoordinate);
            if (route.indexOf(newCoordinate) < route.size() - 1) {
                return true;
            }
        }
        return false;

    }

    public Coordinate getLocation() {
        return taxi.getLocation();
    }

    private void fillInstructions(String input) {
        String[] stringInstructions = input.split(", ");
        for (int i = 0; i < stringInstructions.length; i++) {
            Instruction instruction = createInstruction(stringInstructions[i]);
            instructions.add(instruction);
        }
    }

    private Instruction createInstruction(String stringInstruction) {
        String rotate = stringInstruction.substring(0, 1);
        int stepLength = Integer.parseInt(stringInstruction.substring(1));
        return new Instruction(rotate, stepLength);
    }
}
