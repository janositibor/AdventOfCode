package TZJanosi.y2015.day03;

public enum Direction {
    UP("^", new Coordinate(0, 1)),
    DOWN("v", new Coordinate(0, -1)),
    RIGHT(">", new Coordinate(1, 0)),
    LEFT("<", new Coordinate(-1, 0));
    private String sign;
    private Coordinate direction;

    Direction(String sign, Coordinate direction) {
        this.sign = sign;
        this.direction = direction;
    }

    public String getSign() {
        return sign;
    }

    public Coordinate getDirection() {
        return direction;
    }
}
