package TZJanosi.y2024.day15;

public enum Direction {
    RIGHT(new Coordinate(1,0),'>'), DOWN(new Coordinate(0,1),'v'), LEFT(new Coordinate(-1,0),'<'), UP(new Coordinate(0,-1),'^');
    private Coordinate shift;
    private char sign;

    Direction(Coordinate shift, char sign) {
        this.shift = shift;
        this.sign = sign;
    }

    public Coordinate getShift() {
        return shift;
    }

    public char getSign() {
        return sign;
    }
}
