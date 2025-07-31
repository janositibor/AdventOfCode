package tzjanosi.y2016.day11;

public enum Direction {
    UP(1), DOWN(-1);

    private int value;

    Direction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
