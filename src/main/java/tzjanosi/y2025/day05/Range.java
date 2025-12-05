package tzjanosi.y2025.day05;

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
}
