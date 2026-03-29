package tzjanosi.y2018.day11;

public class Element {
    private Coordinate coordinate;
    private int gridSerialNumber;
    private int power;

    public Element(Coordinate coordinate, int gridSerialNumber) {
        this.coordinate = coordinate;
        this.gridSerialNumber = gridSerialNumber;
        calculatePower();
    }

    private void calculatePower() {
        int rackId = coordinate.getX() + 10;
        int temp = (rackId * coordinate.getY() + gridSerialNumber) * rackId;
        int hundreds = getHundredsFrom(temp);
        power = hundreds - 5;
    }

    private int getHundredsFrom(int input) {
        int temp = input - 1000 * (input / 1000);
        return temp / 100;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public int getPower() {
        return power;
    }

    @Override
    public String toString() {
        return "Element{" +
                "coordinate=" + coordinate +
                ", power=" + power +
                '}';
    }
}
