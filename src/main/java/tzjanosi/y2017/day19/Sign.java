package tzjanosi.y2017.day19;

import java.util.Objects;

public class Sign {
    private char value;
    private boolean letter;
    private Coordinate coordinate;

    public Sign(char value, boolean letter, Coordinate coordinate) {
        this.value = value;
        this.letter = letter;
        this.coordinate = coordinate;
    }

    public char getValue() {
        return value;
    }

    public boolean isLetter() {
        return letter;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Sign sign = (Sign) o;
        return value == sign.value && letter == sign.letter && Objects.equals(coordinate, sign.coordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, letter, coordinate);
    }

    @Override
    public String toString() {
        return "Sign{" +
                "value=" + value +
                ", letter=" + letter +
                ", coordinate=" + coordinate +
                '}';
    }
}
