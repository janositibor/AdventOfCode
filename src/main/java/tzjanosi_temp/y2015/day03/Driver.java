package tzjanosi_temp.y2015.day03;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Driver {
    private Coordinate start = new Coordinate(0, 0);
    private Set<Coordinate> visited = new HashSet<>();
    private String input;

    public Driver(String input) {
        this.input = input;
    }

    private Direction findDirectionToSign(String sign) {
        return Arrays.stream(Direction.values()).filter(d -> d.getSign().equals(sign)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.format("No direction found for this sign: %s", sign)));
    }

    public int countVisitedHouses() {
        run();
        return visited.size();
    }

    public int countVisitedHousesWithRoboSanta() {
        runWithRoboSanta(0);
        runWithRoboSanta(1);
        return visited.size();
    }

    private void run() {
        visited.add(start);
        String[] ordersAsString = input.split("");
        Coordinate actualPosition = start;
        Coordinate newPosition;
        for (int i = 0; i < ordersAsString.length; i++) {
            String sign = ordersAsString[i];
            Direction direction = findDirectionToSign(sign);
            newPosition = actualPosition.move(direction.getDirection());
            visited.add(newPosition);
            actualPosition = newPosition;
        }
    }

    private void runWithRoboSanta(int shift) {
        visited.add(start);
        String[] ordersAsString = input.split("");
        Coordinate actualPosition = start;
        Coordinate newPosition;
        for (int i = shift; i < ordersAsString.length; i += 2) {
            String sign = ordersAsString[i];
            Direction direction = findDirectionToSign(sign);
            newPosition = actualPosition.move(direction.getDirection());
            visited.add(newPosition);
            actualPosition = newPosition;
        }
    }

    public String getInput() {
        return input;
    }
}
