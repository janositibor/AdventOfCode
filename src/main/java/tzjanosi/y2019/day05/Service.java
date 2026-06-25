package tzjanosi.y2019.day05;

import java.util.Optional;

public class Service {
    private int inputNumber;
    private int[] program;

    private class Result {
        private boolean stop;
        private Optional<Integer> output;
        private int shift;

        public Result(boolean stop, Optional<Integer> output, int shift) {
            this.stop = stop;
            this.output = output;
            this.shift = shift;
        }

        public boolean isStop() {
            return stop;
        }

        public int getShift() {
            return shift;
        }

        public Optional<Integer> getOutput() {
            return output;
        }
    }

    public Service(int inputNumber, String input) {
        this.inputNumber = inputNumber;
        processInput(input);
    }

    private void processInput(String input) {
        String[] numbers = input.split(",");
        program = new int[numbers.length];
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

    private Result execute(int index) {
        boolean stop = false;
        Optional<Integer> output = Optional.empty();
        int shift = 4;

        int opcode = program[index];
        int twoDigitOpcode = opcode % 100;
        boolean firstParameterImmediate = (opcode / 100) % 10 == 1;
        boolean secondParameterImmediate = (opcode / 1000) % 10 == 1;
        int firstParameter;
        int secondParameter;
        int target;

        switch (twoDigitOpcode) {
            case 1:
                firstParameter = findParameter(firstParameterImmediate, index + 1);
                secondParameter = findParameter(secondParameterImmediate, index + 2);
                target = program[index + 3];
                program[target] = firstParameter + secondParameter;
                break;
            case 2:
                firstParameter = findParameter(firstParameterImmediate, index + 1);
                secondParameter = findParameter(secondParameterImmediate, index + 2);
                target = program[index + 3];
                program[target] = firstParameter * secondParameter;
                break;
            case 3:
                target = program[index + 1];
                program[target] = inputNumber;
                shift = 2;
                break;
            case 4:
                target = program[index + 1];
                output = Optional.of(program[target]);
                shift = 2;
                break;
            case 99:
                stop = true;
                break;
            default:
                throw new IllegalArgumentException("Unexpected Two-Digit opcode: " + twoDigitOpcode);
        }
        return new Result(stop, output, shift);
    }

    private int findParameter(boolean isImmediate, int index) {
        return isImmediate ? program[index] : program[program[index]];
    }
}
