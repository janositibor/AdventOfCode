package tzjanosi.y2019.day11;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class Service {
    private long inputNumber;
    private List<Long> program = new ArrayList<>();
    private int relativeBase;
    private List<Long> outputNumbers = new ArrayList<>();
    private Coordinate position = new Coordinate(0, 0);
    private Direction direction = Direction.UP;
    private Map<Coordinate, Integer> hull = new ConcurrentHashMap<>();

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

    public Service(String input) {
        processInput(input);
    }

    public void displayHull() {
        List<String> hullAsList = displayHullAsList();
        for (int i = 0; i < hullAsList.size(); i++) {
            System.out.println(hullAsList.get(i));
        }
    }

    public List<String> displayHullAsList() {
        int minX = hull.entrySet().stream().filter(e -> e.getValue().equals(1)).mapToInt(e -> e.getKey().getX()).min().orElseThrow(() -> new IllegalStateException("Empty map!"));
        int maxX = hull.entrySet().stream().filter(e -> e.getValue().equals(1)).mapToInt(e -> e.getKey().getX()).max().orElseThrow(() -> new IllegalStateException("Empty map!"));
        int minY = hull.entrySet().stream().filter(e -> e.getValue().equals(1)).mapToInt(e -> e.getKey().getY()).min().orElseThrow(() -> new IllegalStateException("Empty map!"));
        int maxY = hull.entrySet().stream().filter(e -> e.getValue().equals(1)).mapToInt(e -> e.getKey().getY()).max().orElseThrow(() -> new IllegalStateException("Empty map!"));

        int stringLength = maxX - minX + 1;
        int numberOfStrings = maxY - minY + 1;

        List<String> output = new ArrayList<>();
        String line;
        for (int i = 0; i < numberOfStrings; i++) {
            line = " ".repeat(stringLength);
            output.add(paintALine(i + minY, minX, line));
        }
        return output;
    }

    private String paintALine(int row, int minX, String line) {
        List<Integer> whiteDots = hull.entrySet().stream()
                .filter(e -> e.getKey().getY() == row)
                .filter(e -> e.getValue().equals(1))
                .map(e -> e.getKey().getX() - minX)
                .toList();
        StringBuilder sb = new StringBuilder(line);
        for (Integer i : whiteDots) {
            sb.setCharAt(i, '#');
        }
        return sb.toString();
    }


    private void move(int turn) {
        direction = direction.rotate(turn == 1);
        position = position.shift(direction.getTo());
    }

    private void paintHull(Coordinate coordinate, int color) {
        hull.put(coordinate, color);
    }


    private int findHullColor(Coordinate coordinate) {
        if (hull.containsKey(coordinate)) {
            return hull.get(coordinate);
        }
        return 0;
    }

    private void setInputNumber(long inputNumber) {
        this.inputNumber = inputNumber;
    }

    private void processInput(String input) {
        String[] numbers = input.split(",");
        for (int i = 0; i < numbers.length; i++) {
            program.add(Long.parseLong(numbers[i]));
        }
    }

    public int numberOfPaintedPoints() {
        return hull.size();
    }

    public void run(long startingInput) {
        int index = 0;
        long value;
        setInputNumber(startingInput);
        while (index < program.size()) {
            Result result = execute(index);
            if (result.isStop()) {
                return;
            }
            if (result.getOutput().isPresent()) {
                value = result.getOutput().get();
                outputNumbers.add(value);
                if (outputNumbers.size() == 2) {
                    paintHull(position, outputNumbers.get(0).intValue());
                    move(outputNumbers.get(1).intValue());
                    setInputNumber(findHullColor(position));
                    outputNumbers.clear();
                }
            }
            index += result.getShift();
        }
        throw new IllegalStateException("No halt order found!");
    }

    @SuppressWarnings({"PMD.NcssCount", "PMD.CyclomaticComplexity", "PMD.CognitiveComplexity"})
    private Result execute(int index) {
        boolean stop = false;
        Optional<Long> output = Optional.empty();
        int shift = 4;

        long opcode = program.get(index);
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
                if (target >= program.size()) {
                    extendMemory(target);
                }
                program.set(target, firstParameter + secondParameter);
                break;
            case 2:
                firstParameter = findParameter(firstParameterType, index + 1);
                secondParameter = findParameter(secondParameterType, index + 2);
                target = (int) findTarget(thirdParameterType, index + 3);
                if (target >= program.size()) {
                    extendMemory(target);
                }
                program.set(target, firstParameter * secondParameter);
                break;
            case 3:
                target = (int) findTarget(firstParameterType, index + 1);
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
                shift = (int) (firstParameter == 0 ? 3 : secondParameter - index);
                break;
            case 6:
                firstParameter = findParameter(firstParameterType, index + 1);
                secondParameter = findParameter(secondParameterType, index + 2);
                shift = (int) (firstParameter == 0 ? secondParameter - index : 3);
                break;
            case 7:
                firstParameter = findParameter(firstParameterType, index + 1);
                secondParameter = findParameter(secondParameterType, index + 2);
                target = (int) findTarget(thirdParameterType, index + 3);
                if (target >= program.size()) {
                    extendMemory(target);
                }
                program.set(target, (long) (firstParameter < secondParameter ? 1 : 0));
                break;
            case 8:
                firstParameter = findParameter(firstParameterType, index + 1);
                secondParameter = findParameter(secondParameterType, index + 2);
                target = (int) findTarget(thirdParameterType, index + 3);
                if (target >= program.size()) {
                    extendMemory(target);
                }
                program.set(target, (long) (firstParameter == secondParameter ? 1 : 0));
                break;
            case 9:
                firstParameter = findParameter(firstParameterType, index + 1);
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
