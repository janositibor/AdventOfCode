package TZJanosi.y2024.day21;

public enum ButtonForNumericalPad {
    BUTTON_A("A", new Coordinate(2, 3)),
    BUTTON_0("0", new Coordinate(1, 3)),
    BUTTON_1("1", new Coordinate(0, 2)),
    BUTTON_2("2", new Coordinate(1, 2)),
    BUTTON_3("3", new Coordinate(2, 2)),
    BUTTON_4("4", new Coordinate(0, 1)),
    BUTTON_5("5", new Coordinate(1, 1)),
    BUTTON_6("6", new Coordinate(2, 1)),
    BUTTON_7("7", new Coordinate(0, 0)),
    BUTTON_8("8", new Coordinate(1, 0)),
    BUTTON_9("9", new Coordinate(2, 0));

    private String value;
    private Coordinate coordinate;

    ButtonForNumericalPad(String value, Coordinate coordinate) {
        this.value = value;
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return new Coordinate(coordinate.getX(), coordinate.getY());
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "ButtonForNumericalPad{" +
                "value='" + value + '\'' +
                ", coordinate=" + coordinate +
                '}';
    }
}
