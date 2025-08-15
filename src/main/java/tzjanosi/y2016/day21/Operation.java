package tzjanosi.y2016.day21;

import java.util.Map;
import java.util.Objects;
import java.util.function.UnaryOperator;

public class Operation {
    private OperationType type;
    private int parameter1;
    private int parameter2;
    private Map<OperationType, UnaryOperator<char[]>> runFunctions = Map.ofEntries(
            Map.entry(OperationType.SWAP_POSITION, this::swapPosition),
            Map.entry(OperationType.SWAP_LETTER, this::swapLetter),
            Map.entry(OperationType.ROTATE_LEFT, this::rotateLeft),
            Map.entry(OperationType.ROTATE_RIGHT, this::rotateRight),
            Map.entry(OperationType.ROTATE_BASED_ON_POSITION, this::rotateBasedOnPosition),
            Map.entry(OperationType.REVERSE_POSITIONS, this::reversePositions),
            Map.entry(OperationType.MOVE_POSITION, this::movePosition),
            Map.entry(OperationType.ROTATE_BACK_BASED_ON_POSITION, this::rotateBackBasedOnPosition)
    );


    public Operation(OperationType type, int parameter1, int parameter2) {
        this.type = type;
        this.parameter1 = parameter1;
        this.parameter2 = parameter2;
    }

    public char[] run(char[] input) {
        return runFunctions.get(type).apply(input);
    }

    private char[] rotateBackBasedOnPosition(char[] input) {
        int index = calculatePositionOfCharacter(input);
        if (index <= 1) {
            parameter1 = 1;
            return rotateLeft(input);
        } else {
            if (index % 2 == 1) {
                parameter1 = (index / 2) + 1;
                return rotateLeft(input);
            }
            parameter1 = 3 - (index / 2);
            return rotateRight(input);
        }
    }

    private int calculatePositionOfCharacter(char[] input) {
        int index = -1;
        boolean found = false;
        do {
            index++;
            if (input[index] == parameter1) {
                found = true;
            }
        }
        while (!found);
        return index;
    }


    private char[] swapPosition(char[] input) {
        char[] output = input;
        char temp = output[parameter1];
        output[parameter1] = output[parameter2];
        output[parameter2] = temp;
        return output;
    }

    private char[] swapLetter(char[] input) {
        char[] output = input;
        for (int i = 0; i < output.length; i++) {
            output[i] = swapALetter(output[i]);
        }
        return output;
    }

    private char swapALetter(char input) {
        if (input == parameter1 || input == parameter2) {
            if (input == parameter1) {
                return (char) parameter2;
            }
            return (char) parameter1;
        }
        return input;
    }

    private char[] rotateLeft(char[] input) {
        char[] output = new char[input.length];
        int length = output.length;
        for (int i = 0; i < length; i++) {
            int index = (i - parameter1) % length;
            if (index < 0) {
                index += length;
            }
            output[index] = input[i];
        }
        return output;
    }

    private char[] rotateRight(char[] input) {
        char[] output = new char[input.length];
        int length = input.length;
        for (int i = 0; i < length; i++) {
            int index = (i + parameter1) % length;
            output[index] = input[i];
        }
        return output;
    }

    private char[] rotateBasedOnPosition(char[] input) {
        int numberOfRotation = calculateNumberOfRotation(input);
        parameter1 = numberOfRotation;
        return rotateRight(input);

    }

    private int calculateNumberOfRotation(char[] input) {
        int index = calculatePositionOfCharacter(input);
        if (index >= 4) {
            index++;
        }
        index++;
        return index;
    }

    private char[] reversePositions(char[] input) {
        char[] output = new char[input.length];
        for (int i = 0; i < input.length; i++) {
            if (parameter1 <= i && i <= parameter2) {
                output[i] = input[parameter2 - (i - parameter1)];
            } else {
                output[i] = input[i];
            }
        }
        return output;
    }

    private char[] movePosition(char[] input) {
        char[] output = new char[input.length];
        char temp = input[parameter1];
        int to = 0;
        for (int from = 0; from < input.length; from++) {
            if (from == parameter2) {
                if (parameter1 > parameter2) {
                    output[to] = temp;
                    to++;
                }
                output[to] = input[from];
                to++;
                if (parameter2 > parameter1) {
                    output[to] = temp;
                    to++;
                }
            }
            if (from != parameter1 && from != parameter2) {
                output[to] = input[from];
                to++;
            }
        }
        output[parameter2] = temp;
        return output;
    }

    public OperationType getType() {
        return type;
    }

    public int getParameter1() {
        return parameter1;
    }

    public int getParameter2() {
        return parameter2;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Operation operation = (Operation) o;
        return parameter1 == operation.parameter1 && parameter2 == operation.parameter2 && type == operation.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, parameter1, parameter2);
    }

    @Override
    public String toString() {
        return "Operation{" +
                "type=" + type +
                ", parameter1=" + parameter1 +
                ", parameter2=" + parameter2 +
                '}';
    }
}
