package tzjanosi.y2016.day08;

import java.util.Objects;

public class Pixel {
    private Coordinate coordinate;
    private boolean turnedOn;

    public Pixel(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public boolean inTheColumn(int column) {
        return coordinate.getX() == column;
    }

    public boolean inTheRow(int row) {
        return coordinate.getY() == row;
    }

    public boolean inTheArea(Coordinate other) {
        return coordinate.lessThan(other);
    }

    public int getRow() {
        return coordinate.getY();
    }

    public int getColumn() {
        return coordinate.getX();
    }

    public void setRow(int row) {
        this.coordinate.setY(row);
    }

    public void setColumn(int column) {
        this.coordinate.setX(column);
    }

    public void turnOn() {
        turnedOn = true;
    }

    public boolean isTurnedOn() {
        return turnedOn;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pixel pixel = (Pixel) o;
        return Objects.equals(coordinate, pixel.coordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(coordinate);
    }
}
