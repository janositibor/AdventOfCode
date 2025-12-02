package tzjanosi.y2025.day02;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

public class Range {
    private long from;
    private long to;

    public Range(long from, long to) {
        this.from = from;
        this.to = to;
    }

    public long sumOfInvalidNumbers() {
        LongStream stream = LongStream.rangeClosed(from, to);
        return stream.filter(this::isInvalid).sum();
    }

    public long sumOfInvalidNumbersForPart2() {
        LongStream stream = LongStream.rangeClosed(from, to);
        return stream.filter(this::isInvalidForPart2).sum();
    }

    private boolean isInvalid(long number) {
        String numberAsAString = String.valueOf(number);
        int length = numberAsAString.length();
        if (length % 2 > 0) {
            return false;
        }
        String firstHalf = numberAsAString.substring(0, length / 2);
        String secondHalf = numberAsAString.substring(length / 2);
        return firstHalf.equals(secondHalf);
    }

    private boolean isInvalidForPart2(long number) {
        String numberAsAString = String.valueOf(number);
        int length = numberAsAString.length();
        if (length == 1) {
            return false;
        }
        List<Integer> dividers = findDividers(length);
        for (int i = 0; i < dividers.size(); i++) {
            if (isinvalidDividedBy(numberAsAString, dividers.get(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean isinvalidDividedBy(String numberAsAString, int numberOfParts) {
        int length = numberAsAString.length();
        int shift = length / numberOfParts;
        int from = 0;
        int to = shift;
        String actual = numberAsAString.substring(from, to);
        do {
            from = to;
            to += shift;
            String next = numberAsAString.substring(from, to);
            if (!actual.equals(next)) {
                return false;
            }
        } while (to < length);
        return true;
    }

    private List<Integer> findDividers(int length) {
        List<Integer> output = new ArrayList<>();
        for (int i = 2; i <= length / 2; i++) {
            if (length % i == 0) {
                output.add(i);
            }
        }
        output.add(length);
        return output;
    }
}
