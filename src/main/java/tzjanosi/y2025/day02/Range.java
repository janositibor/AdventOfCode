package tzjanosi.y2025.day02;

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
}
