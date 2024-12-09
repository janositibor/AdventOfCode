package TZJanosi.y2024.day08;

import java.util.List;
import java.util.Objects;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private Coordinate multiplication(int multiplicator){
        return new Coordinate(multiplicator*x,multiplicator*y);
    }
    private Coordinate subtract(Coordinate other){
        return new Coordinate(x-other.x,y- other.y);
    }

    public List<Coordinate> getAntinodes(Coordinate other){
        return List.of(multiplication(2).subtract(other),other.multiplication(2).subtract(this));
    }

    public boolean isInsideArea(Coordinate limit){
        return (x<= limit.x && y<= limit.y) && (0<=x && 0<=y);
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
