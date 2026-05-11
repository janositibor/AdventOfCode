package tzjanosi.y2018.day18;

import java.util.Set;

public class Field {
    private Coordinate position;
    private Acre type;

    public Field(Coordinate position, Acre type) {
        this.position = position;
        this.type = type;
    }

    public void turn(Set<Field> neighbours) {
        type = type.turn(neighbours);
    }

    public Field(Field original) {
        this.position = new Coordinate(original.position);
        this.type = original.type;
    }

    public Coordinate getPosition() {
        return position;
    }

    public Acre getType() {
        return type;
    }
}
