package tzjanosi.y2016.day17;

public enum Direction {
    U(new Coordinate(0, -1)),
    D(new Coordinate(0, 1)),
    L(new Coordinate(-1, 0)),
    R(new Coordinate(1, 0));

    private Coordinate direction;

    Direction(Coordinate direction) {
        this.direction = direction;
    }

    public Coordinate getDirection() {
        return direction;
    }
}
