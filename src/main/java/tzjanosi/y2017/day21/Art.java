package tzjanosi.y2017.day21;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Art {
    private Set<Matrix> matrixes = new HashSet<>();

    public Art(List<String> input) {
        processInput(input);
    }

    private void processInput(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            processLine(input.get(i));
        }
    }

    private void processLine(String line) {
        String[] parts = line.split(" => ");
        Matrix matrix = new Matrix(parts[0].replaceAll("/", ""), parts[1].replaceAll("/", ""));
        matrixes.add(matrix);
    }

    public Set<Matrix> getMatrixes() {
        return matrixes;
    }

    public char[][] getReplacement(char[][] from) {
        for (Matrix matrix : matrixes) {
            if (matrix.equivalentsContains(from)) {
                return matrix.getReplacementValue();
            }
        }
        throw new IllegalStateException("No replacement found for: " + Arrays.deepToString(from));
    }
}
