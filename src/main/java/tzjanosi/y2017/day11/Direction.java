package tzjanosi.y2017.day11;

public enum Direction {
    NORTH("n", new Coordinate(0.0, 1.0)),
    NORTH_EAST("ne", new Coordinate(Math.sqrt(3.0 / 4.0), 1.0 / 2)),
    SOUTH_EAST("se", new Coordinate(Math.sqrt(3.0 / 4.0), -1.0 / 2)),
    SOUTH("s", new Coordinate(0.0, -1.0)),
    SOUTH_WEST("sw", new Coordinate(-Math.sqrt(3.0 / 4.0), -1.0 / 2)),
    NORTH_WEST("nw", new Coordinate(-Math.sqrt(3.0 / 4.0), 1.0 / 2));

    private String sign;
    private Coordinate shift;

    Direction(String sign, Coordinate shift) {
        this.sign = sign;
        this.shift = shift;
    }

    public String getSign() {
        return sign;
    }

    public Coordinate getShift() {
        return shift;
    }
}
