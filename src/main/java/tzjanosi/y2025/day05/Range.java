package tzjanosi.y2025.day05;

import java.util.Objects;

public class Range {
    private long from;
    private long to;

    public Range(long from, long to) {
        this.from = from;
        this.to = to;
    }


    public boolean isInTheRange(long numberToCheck) {
        return from <= numberToCheck && numberToCheck <= to;
    }

    public long numberOfElements() {
        return to - from + 1;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Range range = (Range) o;
        return from == range.from && to == range.to;
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }

    @Override
    public String toString() {
        return "Range{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }

    public long getFrom() {
        return from;
    }

    public long getTo() {
        return to;
    }
}
