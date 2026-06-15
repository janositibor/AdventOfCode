package tzjanosi.y2018.day23;

public class Nanobot {
    private Coordinate position;
    private FourDCoordinate fourDPosition;
    private long radius;

    public Nanobot(Coordinate position, long radius) {
        this.position = position;
        fourDPosition = new FourDCoordinate(position);
        this.radius = radius;
    }

    public boolean isWithinRadius(Nanobot other) {
        return position.manhattanDistanceTo(other.position) <= radius;
    }

    public long getRadius() {
        return radius;
    }

    public FourDCoordinate getFourDPosition() {
        return fourDPosition;
    }
}
