package tzjanosi.y2016.day20;

public class Block {
    private long from;
    private long to;

    public Block(long from, long to) {
        this.from = from;
        this.to = to;
    }

    public boolean isBlocked(long valueToCheck) {
        return from <= valueToCheck && valueToCheck <= to;
    }

    public long getFrom() {
        return from;
    }

    public long getTo() {
        return to;
    }
}
