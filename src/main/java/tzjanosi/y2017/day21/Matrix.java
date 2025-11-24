package tzjanosi.y2017.day21;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Matrix {
    private int size;
    private char[][] value;
    private Set<char[][]> equivalents = new HashSet<>();
    private int numberOfOn;
    private Matrix replacement;

    public Matrix(char[][] value) {
        this.value = value;
        size = value.length;
        countNumberOfOn();
    }

    public Matrix(String asString) {
        size = (int) Math.sqrt(asString.length());
        value = new char[size][size];
        buildValue(asString);
        countNumberOfOn();
        buildEquivalents();
    }


    public Matrix(String asString, String replacementString) {
        this(asString);
        replacement = new Matrix(replacementString);
    }

    public final boolean equivalentsContains(char[][] arrayToCheck) {
        for (char[][] saved : equivalents) {
            if (Arrays.deepEquals(saved, arrayToCheck)) {
                return true;
            }
        }
        return false;
    }

    private void countNumberOfOn() {
        int output = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (value[i][j] == '#') {
                    output++;
                }
            }
        }
        numberOfOn = output;
    }

    private void buildEquivalents() {
        buildRotatedEquivalents(value);
        char[][] verticalMirrored = buildVerticalMirrored();
        buildRotatedEquivalents(verticalMirrored);
    }

    private void buildRotatedEquivalents(char[][] input) {
        char[][] temp = input;
        int counter = 0;
        do {
            if (!equivalentsContains(temp)) {
                equivalents.add(temp);
            }
            temp = buildRotateClockwise(temp);
            counter++;
        }
        while (counter < 4);
    }

    private char[][] buildVerticalMirrored() {
        char[][] output = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                output[i][j] = value[i][size - 1 - j];
            }
        }
        return output;
    }

    private char[][] buildRotateClockwise(char[][] input) {
        char[][] output = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                output[i][j] = input[j][size - 1 - i];
            }
        }
        return output;
    }

    private void buildValue(String asString) {
        char[] inputAsChar = asString.toCharArray();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int index = i * size + j;
                value[i][j] = inputAsChar[index];
            }
        }
    }

    private String equivalentsAsString() {
        StringBuilder output = new StringBuilder();
        for (char[][] array : equivalents) {
            output.append(Arrays.deepToString(array)).append(", ");
        }
        return output.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Matrix matrix = (Matrix) o;
        return Objects.deepEquals(value, matrix.value);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(value);
    }

    @Override
    public String toString() {
        return "Matrix{" +
                "size=" + size +
                ", value=" + Arrays.deepToString(value) +
                ", equivalents=" + equivalentsAsString() +
                ", numberOfOn=" + numberOfOn +
                ", replacement=" + Arrays.deepToString(getReplacementValue()) +
                '}';
    }

    public char[][] getReplacementValue() {
        if (replacement != null) {
            return replacement.getValue();
        }
        return new char[1][1];
    }

    public int getSize() {
        return size;
    }

    public char[][] getValue() {
        return value;
    }

    public int getNumberOfOn() {
        return numberOfOn;
    }

}
