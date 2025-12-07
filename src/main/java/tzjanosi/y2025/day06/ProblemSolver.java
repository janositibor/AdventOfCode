package tzjanosi.y2025.day06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProblemSolver {
    private List<Long> adds = new ArrayList<>();
    private List<Long> multiplications = new ArrayList<>();
    private List<Long> result = new ArrayList<>();
    private List<Long> part2Numbers;
    private List<Long> part2Result = new ArrayList<>();
    private char[][] inputMatrix;

    public ProblemSolver(List<String> input) {
        processInput(input);
        processInputForPart2(input);
    }

    public long calculateGrandTotalForPart2() {
        buildPart2Result();
        return part2Result.stream().mapToLong(Long::longValue).sum();
    }

    private void buildPart2Result() {
        part2Numbers = new ArrayList<>();
        for (int i = inputMatrix.length - 1; i >= 0; i--) {
            i -= processMatrixRow(inputMatrix[i]);
        }
    }

    private int processMatrixRow(char[] input) {
        char[] numberAsArray = Arrays.copyOf(input, input.length - 1);
        long value = Long.parseLong(new String(numberAsArray).trim());
        part2Numbers.add(value);
        char lastChar = input[input.length - 1];
        if (lastChar == '+' || lastChar == '*') {
            long result;
            if (lastChar == '+') {
                result = addPart2Numbers();
            } else {
                result = multiplyPart2Numbers();
            }
            part2Result.add(result);
            part2Numbers = new ArrayList<>();
            return 1;
        }
        return 0;
    }

    private long multiplyPart2Numbers() {
        return part2Numbers.stream().reduce(1L, (d1, d2) -> d1 * d2);
    }

    private long addPart2Numbers() {
        return part2Numbers.stream().reduce(0L, (d1, d2) -> d1 + d2);
    }

    private void processInputForPart2(List<String> input) {
        initInputMatrix(input);
        for (int i = 0; i < input.size(); i++) {
            addLineToInputMatrix(i, input.get(i));
        }
        inputMatrix = transpose(inputMatrix);
    }

    private void initInputMatrix(List<String> input) {
        int y = input.size();
        int x = input.stream().mapToInt(String::length).max().getAsInt();
        inputMatrix = new char[y][x];
        for (char[] row : inputMatrix) {
            Arrays.fill(row, ' ');
        }
    }

    private void addLineToInputMatrix(int y, String line) {
        char[] lineAsCharArray = line.toCharArray();
        if (lineAsCharArray.length == inputMatrix[y].length) {
            inputMatrix[y] = lineAsCharArray;
        } else {
            copyValuesIntoInputMatrix(y, lineAsCharArray);
        }
    }

    private void copyValuesIntoInputMatrix(int y, char[] lineAsCharArray) {
        for (int i = 0; i < lineAsCharArray.length; i++) {
            inputMatrix[y][i] = lineAsCharArray[i];
        }
    }

    private char[][] transpose(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        char[][] transposed = new char[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposed[j][i] = matrix[i][j];
            }
        }
        return transposed;
    }

    public long calculateGrandTotal() {
        return result.stream().mapToLong(Long::longValue).sum();
    }

    private void processInput(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            String[] parts = input.get(i).trim().split("\\s+");
            if (i == 0) {
                initResultLists(parts);
            } else if (i < input.size() - 1) {
                calculate(parts);
            } else {
                buildResultList(parts);
            }
        }
    }

    private void buildResultList(String[] parts) {
        for (int i = 0; i < parts.length; i++) {
            if ("+".equals(parts[i])) {
                result.add(adds.get(i));
            } else {
                result.add(multiplications.get(i));
            }
        }
    }

    private void calculate(String[] parts) {
        for (int i = 0; i < parts.length; i++) {
            long value = Long.parseLong(parts[i]);
            adds.set(i, adds.get(i) + value);
            multiplications.set(i, multiplications.get(i) * value);
        }
    }

    private void initResultLists(String[] parts) {
        for (int i = 0; i < parts.length; i++) {
            long value = Long.parseLong(parts[i]);
            adds.add(value);
            multiplications.add(value);
        }
    }

    public char[][] getInputMatrix() {
        return inputMatrix;
    }
}
