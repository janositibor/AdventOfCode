package tzjanosi_temp.y2024.day12;

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

    public Set<Coordinate> getNeighbours(Coordinate limit){
        Set<Coordinate> result=new HashSet<>();
        Coordinate neighbour;
        for (int i = -1; i <= 1; i+=2) {
            neighbour=new Coordinate(x+i,y);
            if(neighbour.isInsideArea(limit)){
                result.add(neighbour);
            }
        }
        for (int j = -1; j <= 1; j+=2) {
            neighbour=new Coordinate(x,y+j);
            if(neighbour.isInsideArea(limit)){
                result.add(neighbour);
            }
        }
        return result;
    }

    public Coordinate add(Coordinate shift){
        return new Coordinate(x-shift.x,y- shift.y);
    }
    public boolean isInsideArea(Coordinate limit){
        return (x< limit.x && y< limit.y) && (0<=x && 0<=y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
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
