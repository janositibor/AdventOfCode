package TZJanosi.y2024.day12;

import java.util.Set;

public class Point {
    private Coordinate coordinate;
    private char value;

    public Point(Coordinate coordinate, char value) {
        this.coordinate = coordinate;
        this.value = value;
    }

    public boolean isGoodNeighbour(Point other,Coordinate limit){
        if(valueMatchWithReplaceCharacter(other.getValue()) && other.getCoordinate().isInsideArea(limit)){
            return true;
        }
        return false;
    }

    private boolean valueMatchWithReplaceCharacter(char value) {
        if (value==this.value || value==Service.REPLACE_CHARACTER){
            return true;
        }
        return false;
    }

    public char getValue() {
        return value;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }


    public boolean isSameNeighbour(Point other,Coordinate limit) {
        if(other.getValue()==value && other.getCoordinate().isInsideArea(limit)){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Point{" +
                "coordinate=" + coordinate +
                ", value=" + value +
                '}';
    }
}
