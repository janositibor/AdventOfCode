package tzjanosi_temp.y2015.day09;

import java.util.List;

public class Handler {
    private List<String> input;
    private int minimalDistance = Integer.MAX_VALUE;
    private int maximalDistance;

    public Handler(List<String> input) {
        this.input = input;
    }

    public int findShortestWay() {
        RoutePlanner start = new RoutePlanner(input);
        buildShortestWay(start);
        return minimalDistance;
    }

    public int findLongestWay() {
        RoutePlanner start = new RoutePlanner(input);
        buildLongestWay(start);
        return maximalDistance;
    }

    private void buildLongestWay(RoutePlanner actual) {
        int actualDistance = actual.getDistance();

        List<String> destinations = actual.getDestinations();
        if (destinations.isEmpty()) {
            if (actualDistance > maximalDistance) {
                maximalDistance = actualDistance;
            }
            return;
        }
        for (int i = 0; i < destinations.size(); i++) {
            RoutePlanner next = new RoutePlanner(actual);
            String destination = destinations.get(i);
            next.go(destination);
            buildLongestWay(next);
        }
    }

    public void buildShortestWay(RoutePlanner actual) {
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
            buildShortestWay(next);
        }
    }
}
