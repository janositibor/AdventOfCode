package tzjanosi.y2016.day02;

import java.util.ArrayList;
import java.util.List;

public class KeyPadHandler {
    private KeyPad keyPad;

    public KeyPadHandler(List<String> input) {
        List<List<Direction>> instructions = buildInstructions(input);
        keyPad = new KeyPad(instructions);
    }

    public String getNumbers() {
        keyPad.calculateOutput();
        return keyPad.getOutput().toString();
    }

    private List<List<Direction>> buildInstructions(List<String> input) {
        List<List<Direction>> output = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            List<Direction> line = buildLineOfInstructions(input.get(i));
            output.add(line);
        }
        return output;
    }

    private List<Direction> buildLineOfInstructions(String line) {
        List<Direction> output = new ArrayList<>();
        String[] lineAsStringArray = line.split("");
        for (int i = 0; i < lineAsStringArray.length; i++) {
            output.add(Direction.valueOf(lineAsStringArray[i]));
        }
        return output;
    }
}
