package tzjanosi_temp.y2024.day12;

public class Point {
    private Coordinate coordinate;
    private char value;

    public Point(Coordinate coordinate, char value) {
        this.coordinate = coordinate;
        this.value = value;
    }

    public boolean isGoodNeighbour(Point other,Coordinate limit){
        return valueMatchWithReplaceCharacter(other.getValue()) && other.getCoordinate().isInsideArea(limit);
    }

    private boolean valueMatchWithReplaceCharacter(char value) {
        return value == this.value || value == Service.REPLACE_CHARACTER;
    }

    public char getValue() {
        return value;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }


    public boolean isSameNeighbour(Point other,Coordinate limit) {
        return other.getValue() == value && other.getCoordinate().isInsideArea(limit);
    }

    @Override
    public String toString() {
        return "Point{" +
                "coordinate=" + coordinate +
                ", value=" + value +
                '}';
    }
}
