package tzjanosi.y2018.day06;

public class LightHouse {
    private char name;
    private Coordinate coordinate;

    public LightHouse(char name, Coordinate coordinate) {
        this.name = name;
        this.coordinate = coordinate;
    }

    public char getName() {
        return name;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}
