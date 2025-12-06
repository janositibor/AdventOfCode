package tzjanosi.y2025.day06;

import java.util.ArrayList;
import java.util.List;

public class ProblemSolver {
    private List<Long> adds = new ArrayList<>();
    private List<Long> multiplications = new ArrayList<>();
    private List<Long> result = new ArrayList<>();

    public ProblemSolver(List<String> input) {
        processInput(input);
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
}
