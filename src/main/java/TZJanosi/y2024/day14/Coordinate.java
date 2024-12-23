package TZJanosi.y2024.day14;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Coordinate(Coordinate original){
        x = original.x;
        y = original.y;
    }

    public Coordinate multiply(int multiplicator){
        return new Coordinate(x*multiplicator,y*multiplicator);
    }
    public void shift(Coordinate shift){
        x+=shift.x;
        y+=shift.y;
    }public Coordinate add(Coordinate shift){
        return new Coordinate(x+shift.x,y+shift.y);
    }
    public boolean isInsideArea(Coordinate limit){
        return (x< limit.x && y< limit.y) && (0<=x && 0<=y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
