package tzjanosi.y2025.day02;

import java.util.ArrayList;
import java.util.List;

public class IDValidator {
    private List<Range> ranges = new ArrayList<>();

    public IDValidator(String input) {
        processInput(input);
    }

    public long calculateSumOfInvalidIds() {
        return ranges.stream()
                .mapToLong(Range::sumOfInvalidNumbers)
                .sum();
    }

    public long calculateSumOfInvalidIdsForPart2() {
        return ranges.stream()
                .mapToLong(Range::sumOfInvalidNumbersForPart2)
                .sum();
    }

    private void processInput(String line) {
        String[] parts = line.split(",");
        for (int i = 0; i < parts.length; i++) {
            Range range = createRangeFromPartOfInput(parts[i]);
            ranges.add(range);
        }
    }

    private Range createRangeFromPartOfInput(String part) {
        String[] numbersAsString = part.split("-");
        long from = Long.parseLong(numbersAsString[0]);
        long to = Long.parseLong(numbersAsString[1]);
        return new Range(from, to);
    }
}
