package TZJanosi.y2024.day21;

public enum ButtonForDirectionalPad {
    BUTTON_A("A", new Coordinate(2, 0)),
    BUTTON_UP("^", new Coordinate(1, 0)),
    BUTTON_LEFT("<", new Coordinate(0, 1)),
    BUTTON_DOWN("v", new Coordinate(1, 1)),
    BUTTON_RIGHT(">", new Coordinate(2, 1));

    private String value;
    private Coordinate coordinate;

    ButtonForDirectionalPad(String value, Coordinate coordinate) {
        this.value = value;
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return new Coordinate(coordinate.getX(), coordinate.getY());
    }

    public String getValue() {
        return value;
    }
}
