package tzjanosi_temp.y2015.day06.part2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Network {
    private int size;
    private Grid grid;
    private List<Order> orders = new ArrayList<>();

    public Network(int size, List<String> input) {
        this.size = size;
        grid = new Grid(size);
        buildOrders(input);
    }

    private void buildOrders(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            processLine(line);
        }
    }

    private void processLine(String line) {
        String[] words = line.split(" ");
        int shift = 0;
        if (words.length == 5) {
            shift++;
        }
        String operatorName = words[shift];
        String from = words[shift + 1];
        String to = words[shift + 3];
        createOrder(operatorName, from, to);
    }

    private void createOrder(String operatorName, String from, String to) {
        Operator operator = findOperatorByName(operatorName);
        Coordinate fromCoordinate = createCoordinate(from);
        Coordinate toCoordinate = createCoordinate(to);
        Order order = new Order(operator, fromCoordinate, toCoordinate);
        orders.add(order);
    }

    private Coordinate createCoordinate(String input) {
        String[] numbers = input.split(",");
        Coordinate coordinate = new Coordinate(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1]));
        coordinate.setLimit(size);
        return coordinate;
    }

    private Operator findOperatorByName(String operatorName) {
        return Arrays.stream(Operator.values())
                .filter(o -> o.getNameAsString().equals(operatorName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No operator found with name: " + operatorName));
    }

    public void run() {
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            order.execute(grid);
        }
    }

    public int countActiveBulbs() {
        return grid.countActiveBulbs();
    }
}
