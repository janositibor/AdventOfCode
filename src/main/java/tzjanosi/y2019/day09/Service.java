package tzjanosi.y2019.day09;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Service {
    private long inputNumber;
    //    private int[] program;
    private List<Long> program = new ArrayList<>();
    private int relativeBase;
    private List<Long> outputNumbers = new ArrayList<>();

    private class Result {
        private boolean stop;
        private Optional<Long> output;
        private int shift;

        public Result(boolean stop, Optional<Long> output, int shift) {
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

        public Optional<Long> getOutput() {
            return output;
        }
    }

    public Service(int inputNumber, String input) {
        this.inputNumber = inputNumber;
        processInput(input);
    }

    private void processInput(String input) {
        String[] numbers = input.split(",");
        for (int i = 0; i < numbers.length; i++) {
            program.add(Long.parseLong(numbers[i]));
        }
    }

    public List<Long> runConveyorBelt() {
        int index = 0;
        long value;
        while (index < program.size()) {
            Result result = execute(index);
            if (result.isStop()) {
                return outputNumbers;
            }
            if (result.getOutput().isPresent()) {
                value = result.getOutput().get();
                outputNumbers.add(value);
            }
            index += result.getShift();
        }
        throw new IllegalStateException("No halt order found!");
    }

    public long run() {
        int index = 0;
        long value = 0;
        while (index < program.size()) {
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

    @SuppressWarnings({"PMD.NcssCount", "PMD.CyclomaticComplexity", "PMD.CognitiveComplexity"})
    private Result execute(int index) {
//        System.out.println("index: "+index);
        boolean stop = false;
        Optional<Long> output = Optional.empty();
        int shift = 4;

        long opcode = program.get(index);
//        System.out.println("opcode: "+opcode);
        int twoDigitOpcode = (int) opcode % 100;
        int firstParameterType = (int) (opcode / 100) % 10;
        int secondParameterType = (int) (opcode / 1000) % 10;
        int thirdParameterType = (int) (opcode / 10000) % 10;
        long firstParameter;
        long secondParameter;
        int target;

        switch (twoDigitOpcode) {
            case 1:
                firstParameter = findParameter(firstParameterType, index + 1);
                secondParameter = findParameter(secondParameterType, index + 2);
                target = (int) findTarget(thirdParameterType, index + 3);
//                System.out.println(firstParameter+", "+secondParameter+", "+target+"\n");
                if (target >= program.size()) {
                    extendMemory(target);
                }
                program.set(target, firstParameter + secondParameter);
                break;
            case 2:
                firstParameter = findParameter(firstParameterType, index + 1);
                secondParameter = findParameter(secondParameterType, index + 2);
                target = (int) findTarget(thirdParameterType, index + 3);
//                System.out.println(firstParameter+", "+secondParameter+", "+target+"\n");
                if (target >= program.size()) {
                    extendMemory(target);
                }
                program.set(target, firstParameter * secondParameter);
                break;
            case 3:
                target = (int) findTarget(firstParameterType, index + 1);
//                System.out.println(target+"\n");
                if (target >= program.size()) {
                    extendMemory(target);
                }

                program.set(target, inputNumber);
                shift = 2;
                break;
            case 4:
                output = Optional.of(findParameter(firstParameterType, index + 1));
                shift = 2;
                break;
            case 5:
                firstParameter = findParameter(firstParameterType, index + 1);
                secondParameter = findParameter(secondParameterType, index + 2);
//                System.out.println(firstParameter+", "+secondParameter+"\n");
                shift = (int) (firstParameter == 0 ? 3 : secondParameter - index);
                break;
            case 6:
                firstParameter = findParameter(firstParameterType, index + 1);
                secondParameter = findParameter(secondParameterType, index + 2);
//                System.out.println(firstParameter+", "+secondParameter+", "+"\n");
                shift = (int) (firstParameter == 0 ? secondParameter - index : 3);
                break;
            case 7:
                firstParameter = findParameter(firstParameterType, index + 1);
                secondParameter = findParameter(secondParameterType, index + 2);
                target = (int) findTarget(thirdParameterType, index + 3);
//                System.out.println(firstParameter+", "+secondParameter+", "+target+"\n");
                if (target >= program.size()) {
                    extendMemory(target);
                }
                program.set(target, (long) (firstParameter < secondParameter ? 1 : 0));
                break;
            case 8:
                firstParameter = findParameter(firstParameterType, index + 1);
                secondParameter = findParameter(secondParameterType, index + 2);
                target = (int) findTarget(thirdParameterType, index + 3);
//                System.out.println(firstParameter+", "+secondParameter+", "+target+"\n");
                if (target >= program.size()) {
                    extendMemory(target);
                }
                program.set(target, (long) (firstParameter == secondParameter ? 1 : 0));
                break;
            case 9:
                firstParameter = findParameter(firstParameterType, index + 1);
//                System.out.println(firstParameter+"\n");
                relativeBase += firstParameter;
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

    private void extendMemory(int target) {
        while (program.size() < target + 1) {
            program.add(0L);
        }
    }

    private long findParameter(int parameterType, int index) {
        int position;
        switch (parameterType) {
            case 0:
                position = program.get(index).intValue();
                return (position < program.size()) ? program.get(position) : 0;
            case 1:
                return program.get(index);
            case 2:
//                System.out.println("relativeBase: "+relativeBase);
                position = relativeBase + program.get(index).intValue();
                return position < program.size() ? program.get(position) : 0;
            default:
                throw new IllegalArgumentException("Unexpected parameter type: " + parameterType);
        }
    }

    private long findTarget(int parameterType, int index) {
        int position;
        switch (parameterType) {
            case 0:
                position = program.get(index).intValue();
                return position;
            case 2:
                position = relativeBase + program.get(index).intValue();
                return position;
            default:
                throw new IllegalArgumentException("Unexpected parameter type: " + parameterType);
        }
    }
}
