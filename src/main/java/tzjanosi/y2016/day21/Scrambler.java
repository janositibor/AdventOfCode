package tzjanosi.y2016.day21;

import java.util.ArrayList;
import java.util.List;

public class Scrambler {
    private List<Operation> operations = new ArrayList<>();

    public Scrambler(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            process(input.get(i));
        }
    }

    public String createPassword(String input) {
        char[] password = input.toCharArray();
        for (int i = 0; i < operations.size(); i++) {
            Operation operation = operations.get(i);
            password = operation.run(password);
        }
        return new String(password);
    }

    private void process(String line) {
        String[] words = line.split(" ");
        Operation operation = createOperation(words);
        operations.add(operation);
    }

    private Operation createOperation(String[] words) {
        String prefix = words[0] + " " + words[1];
        Operation output;
        switch (prefix) {
            case "swap position":
                output = new Operation(OperationType.SWAP_POSITION, Integer.parseInt(words[2]), Integer.parseInt(words[5]));
                break;
            case "swap letter":
                output = new Operation(OperationType.SWAP_LETTER, words[2].charAt(0), words[5].charAt(0));
                break;
            case "rotate left":
                output = new Operation(OperationType.ROTATE_LEFT, Integer.parseInt(words[2]), 0);
                break;
            case "rotate right":
                output = new Operation(OperationType.ROTATE_RIGHT, Integer.parseInt(words[2]), 0);
                break;
            case "rotate based":
                output = new Operation(OperationType.ROTATE_BASED_ON_POSITION, words[6].charAt(0), 0);
                break;
            case "reverse positions":
                output = new Operation(OperationType.REVERSE_POSITIONS, Integer.parseInt(words[2]), Integer.parseInt(words[4]));
                break;
            case "move position":
                output = new Operation(OperationType.MOVE_POSITION, Integer.parseInt(words[2]), Integer.parseInt(words[5]));
                break;
            default:
                throw new IllegalArgumentException("No operation found: " + prefix);
        }
        return output;
    }

    public List<Operation> getOperations() {
        return operations;
    }
}
