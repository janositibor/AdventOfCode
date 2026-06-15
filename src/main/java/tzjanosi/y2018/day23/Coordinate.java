package tzjanosi.y2018.day23;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coordinate that = (Coordinate) o;
        return x == that.x && y == that.y && z == that.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public long getZ() {
        return z;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
