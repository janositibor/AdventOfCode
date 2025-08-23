package tzjanosi.y2016.day22;

public class NodeForPathFinder {
    private Coordinate coordinate;
    private int step;

    public NodeForPathFinder(Coordinate coordinate, int step) {
        this.coordinate = coordinate;
        this.step = step;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public int getStep() {
        return step;
    }
}
