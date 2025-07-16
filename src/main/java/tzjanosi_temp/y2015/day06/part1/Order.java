package tzjanosi_temp.y2015.day06.part1;

public class Order {
    private Operator operator;
    private Coordinate from;
    private Coordinate to;

    public Order(Operator operator, Coordinate from, Coordinate to) {
        this.operator = operator;
        this.from = from;
        this.to = to;
    }

    public void execute(Grid grid) {
        for (int i = from.getY(); i <= to.getY(); i++) {
            for (int j = from.getX(); j <= to.getX(); j++) {
                Bulb bulb = grid.findBulbAtPosition(i, j);
                operator.operation(bulb);
            }
        }
    }
}
