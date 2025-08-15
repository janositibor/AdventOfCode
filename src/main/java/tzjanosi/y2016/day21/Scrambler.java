package tzjanosi.y2016.day21;

import java.util.ArrayList;
import java.util.List;

public class Scrambler {
    private List<Operation> operations = new ArrayList<>();
    private List<Operation> reversedOperations = new ArrayList<>();

    public Scrambler(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            process(input.get(i));
        }
        reversedOperations = reversedOperations.reversed();
    }

    public String decryptPassword(String input) {
        char[] password = input.toCharArray();
        for (int i = 0; i < reversedOperations.size(); i++) {
            Operation operation = reversedOperations.get(i);
            password = operation.run(password);
        }
        return new String(password);
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
        Operation reverse = createReverseOperation(operation);
        reversedOperations.add(reverse);
    }

    private Operation createReverseOperation(Operation operationToReverse) {
        switch (operationToReverse.getType()) {
            case SWAP_POSITION:
                return reverseForSwapPosition(operationToReverse);
            case SWAP_LETTER:
                return reverseForSwapLetter(operationToReverse);
            case ROTATE_LEFT:
                return reverseForRotateLeft(operationToReverse);
            case ROTATE_RIGHT:
                return reverseForRotateRight(operationToReverse);
            case ROTATE_BASED_ON_POSITION:
                return reverseForRotateBasedOnPosition(operationToReverse);
            case REVERSE_POSITIONS:
                return reverseForReversePositions(operationToReverse);
            case MOVE_POSITION:
                return reverseForMovePosition(operationToReverse);
            default:
                throw new IllegalArgumentException("No operation found: " + operationToReverse.getType());
        }
    }

    private Operation reverseForMovePosition(Operation operationToReverse) {
        return new Operation(OperationType.MOVE_POSITION, operationToReverse.getParameter2(), operationToReverse.getParameter1());
    }

    private Operation reverseForReversePositions(Operation operationToReverse) {
        return operationToReverse;
    }

    private Operation reverseForRotateBasedOnPosition(Operation operationToReverse) {
        return new Operation(OperationType.ROTATE_BACK_BASED_ON_POSITION, operationToReverse.getParameter1(), 0);
    }

    private Operation reverseForRotateRight(Operation operationToReverse) {
        return new Operation(OperationType.ROTATE_LEFT, operationToReverse.getParameter1(), 0);
    }

    private Operation reverseForRotateLeft(Operation operationToReverse) {
        return new Operation(OperationType.ROTATE_RIGHT, operationToReverse.getParameter1(), 0);
    }

    private Operation reverseForSwapLetter(Operation operationToReverse) {
        return operationToReverse;
    }

    private Operation reverseForSwapPosition(Operation operationToReverse) {
        return operationToReverse;
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
