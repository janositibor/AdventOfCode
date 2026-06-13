package tzjanosi.y2018.day23;

public class Nanobot {
    private Coordinate position;
    private long radius;

    public Nanobot(Coordinate position, long radius) {
        this.position = position;
        this.radius = radius;
    }

    public boolean isWithinRadius(Nanobot other) {
        return position.manhattanDistanceTo(other.position) <= radius;
    }

    public long getRadius() {
        return radius;
    }
}
