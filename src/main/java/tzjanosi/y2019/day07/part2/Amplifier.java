package tzjanosi.y2019.day07.part2;

import java.util.Optional;

public class Amplifier {
    private long phase;
    private long inputNumber;
    private long[] program;
    private boolean usePhase = true;
    private int index;

    public Amplifier(long phase, String input) {
        this.phase = phase;
        processInput(input);
    }

    private void processInput(String input) {
        String[] numbers = input.split(",");
        program = new long[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            program[i] = Integer.parseInt(numbers[i]);
        }
    }

    public Optional<Long> run(long inputValue) {
        inputNumber = inputValue;
        while (index < program.length) {
            Result result = execute(index);
            index += result.getShift();
            if (result.isStop()) {
                return Optional.empty();
            }
            if (result.getOutput().isPresent()) {
                return Optional.of(result.getOutput().get());
            }

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
                long value;
                if (usePhase) {
                    value = phase;
                    usePhase = false;
                } else {
                    value = inputNumber;
                }
                program[target] = value;
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
