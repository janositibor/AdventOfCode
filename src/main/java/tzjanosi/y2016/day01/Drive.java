package tzjanosi.y2016.day01;

import java.util.ArrayList;
import java.util.List;

public class Drive {
    private Taxi taxi = new Taxi();
    private List<Instruction> instructions = new ArrayList<>();

    public Drive(String input) {
        fillInstructions(input);
    }

    public int process() {
        for (int i = 0; i < instructions.size(); i++) {
            taxi.move(instructions.get(i));
        }
        return taxi.calculateDistance();
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
