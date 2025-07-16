package tzjanosi.y2024.day15;

public class MapObject {
    private Coordinate position;
    private boolean moveable;
    private char sign;

    public MapObject(Coordinate position, boolean moveable, char sign) {
        this.position = position;
        this.moveable = moveable;
        this.sign = sign;
    }

    public Coordinate getPosition() {
        return position;
    }

    public boolean isMoveable() {
        return moveable;
    }

    public char getSign() {
        return sign;
    }

    @Override
    public String toString() {
        return "MapObject{" +
                "position=" + position +
                ", sign=" + sign +
                '}';
    }
}
