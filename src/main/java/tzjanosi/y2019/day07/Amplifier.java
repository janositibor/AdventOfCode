package tzjanosi.y2019.day07;

import java.util.Optional;

public class Amplifier {
    private long[] inputNumber;
    private long[] program;
    private int whichInput;


    public Amplifier(long inputNumber[], String input) {
        this.inputNumber = inputNumber;
        processInput(input);
    }

    private void processInput(String input) {
        String[] numbers = input.split(",");
        program = new long[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            program[i] = Integer.parseInt(numbers[i]);
        }
    }

    public long run() {
        int index = 0;
        long value = 0;
        while (index < program.length) {
            Result result = execute(index);
            if (result.isStop()) {
                return value;
            }
            if (result.getOutput().isPresent()) {
                value = result.getOutput().get();
            }
            index += result.getShift();
        }
        throw new IllegalStateException("No halt order found!");
    }

    @SuppressWarnings({"PMD.NcssCount", "PMD.CyclomaticComplexity"})
    private Result execute(int index) {
        boolean stop = false;
        Optional<Long> output = Optional.empty();
        int shift = 4;

        long opcode = program[index];
        int twoDigitOpcode = (int) opcode % 100;
        boolean firstParameterImmediate = (opcode / 100) % 10 == 1;
        boolean secondParameterImmediate = (opcode / 1000) % 10 == 1;
        long firstParameter;
        long secondParameter;
        int target;

        switch (twoDigitOpcode) {
            case 1:
                firstParameter = findParameter(firstParameterImmediate, index + 1);
                secondParameter = findParameter(secondParameterImmediate, index + 2);
                target = (int) program[index + 3];
                program[target] = firstParameter + secondParameter;
                break;
            case 2:
                firstParameter = findParameter(firstParameterImmediate, index + 1);
                secondParameter = findParameter(secondParameterImmediate, index + 2);
                target = (int) program[index + 3];
                program[target] = firstParameter * secondParameter;
                break;
            case 3:
                target = (int) program[index + 1];
                program[target] = inputNumber[whichInput];
                whichInput++;
                shift = 2;
                break;
            case 4:
                output = Optional.of(findParameter(firstParameterImmediate, index + 1));
                shift = 2;
                break;
            case 5:
                firstParameter = findParameter(firstParameterImmediate, index + 1);
                secondParameter = findParameter(secondParameterImmediate, index + 2);
                shift = (int) (firstParameter == 0 ? 3 : secondParameter - index);
                break;
            case 6:
                firstParameter = findParameter(firstParameterImmediate, index + 1);
                secondParameter = findParameter(secondParameterImmediate, index + 2);
                shift = (int) (firstParameter == 0 ? secondParameter - index : 3);
                break;
            case 7:
                firstParameter = findParameter(firstParameterImmediate, index + 1);
                secondParameter = findParameter(secondParameterImmediate, index + 2);
                target = (int) program[index + 3];
                program[target] = (firstParameter < secondParameter ? 1 : 0);
                break;
            case 8:
                firstParameter = findParameter(firstParameterImmediate, index + 1);
                secondParameter = findParameter(secondParameterImmediate, index + 2);
                target = (int) program[index + 3];
                program[target] = (firstParameter == secondParameter ? 1 : 0);
                break;
            case 99:
                stop = true;
                break;
            default:
                throw new IllegalArgumentException("Unexpected Two-Digit opcode: " + twoDigitOpcode);
        }
        return new Result(stop, output, shift);
    }

    private long findParameter(boolean isImmediate, int index) {

        return isImmediate ? program[index] : program[((int) program[index])];
    }
}
