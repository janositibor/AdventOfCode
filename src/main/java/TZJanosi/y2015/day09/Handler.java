package TZJanosi.y2015.day09;

import java.util.List;

public class Handler {
    private List<String> input;
    private int minimalDistance = Integer.MAX_VALUE;

    public Handler(List<String> input) {
        this.input = input;
    }

    public int findShortestWay() {
        RoutePlanner start = new RoutePlanner(input);
        buildWay(start);
        return minimalDistance;
    }

    public void buildWay(RoutePlanner actual) {
        int actualDistance = actual.getDistance();

        if (actualDistance > minimalDistance) {
            return;
        }
        List<String> destinations = actual.getDestinations();
        if (destinations.isEmpty()) {
            if (actualDistance < minimalDistance) {
                minimalDistance = actualDistance;
            }
            return;
        }
        for (int i = 0; i < destinations.size(); i++) {
            RoutePlanner next = new RoutePlanner(actual);
            String destination = destinations.get(i);
            next.go(destination);
            buildWay(next);
        }
    }
}
