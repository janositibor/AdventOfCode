package tzjanosi.y2017.day21;

import java.util.Arrays;
import java.util.List;

public class Paint {
    private char[][] value = {{'.', '#', '.'}, {'.', '.', '#'}, {'#', '#', '#'}};
    private Art art;

    public Paint(List<String> rules) {
        art = new Art(rules);
    }

    public int numberOfOnsAfterSteps(int count) {
        return new Matrix(nextRepeated(count)).getNumberOfOn();
    }

    public char[][] nextRepeated(int count) {
        int counter = 0;
        char[][] temp = value;
        while (counter < count) {
            temp = next(temp);
            counter++;
        }
        return temp;
    }

    private char[][] next(char[][] input) {
        int actualSize = input.length;
        if (actualSize % 2 == 0) {
            return createNext(input, 2);
        }
        if (actualSize % 3 == 0) {
            return createNext(input, 3);
        }
        throw new IllegalArgumentException("Unexpected size of input Matrix: " + Arrays.deepToString(input));
    }

    private char[][] createNext(char[][] input, int divider) {
        int numberOfBlocks = input.length / divider;
        char[][] output = new char[0][];
        for (int i = 0; i < numberOfBlocks; i++) {
            char[][] row = new char[0][];
            for (int j = 0; j < numberOfBlocks; j++) {
                char[][] extracted = extractSub(input, divider, i, j);
                char[][] newPart = art.getReplacement(extracted);
                row = (j == 0 ? newPart : MatrixUtils2D.concatCols(row, newPart));
            }
            output = (i == 0 ? row : MatrixUtils2D.concatRows(output, row));
        }
        return output;
    }

    private char[][] extractSub(char[][] input, int divider, int rowBlock, int columnBlock) {
        int startRow = rowBlock * divider;
        int numRows = divider;
        int startCol = columnBlock * divider;
        int numCols = divider;
        char[][] output = new char[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            System.arraycopy(input[startRow + i], startCol, output[i], 0, numCols);
        }
        return output;
    }
}
