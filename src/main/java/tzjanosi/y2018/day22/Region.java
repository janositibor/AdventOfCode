package tzjanosi.y2018.day22;

public class Region {
    private Coordinate location;
    private long erosion;

    public Region(Coordinate location) {
        this.location = location;
    }

    public void setErosion(long erosion) {
        this.erosion = erosion;
    }

    public long getErosion() {
        return erosion;
    }

    public int getValue() {
        return (int) (erosion % 3);
    }

    public Coordinate getLocation() {
        return location;
    }
}
