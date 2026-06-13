package tzjanosi.y2018.day23;

public class Coordinate {
    private long x;
    private long y;
    private long z;

    public Coordinate(long x, long y, long z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public long manhattanDistanceTo(Coordinate other) {
        return Math.abs(x - other.x) + Math.abs(y - other.y) + Math.abs(z - other.z);
    }
}
