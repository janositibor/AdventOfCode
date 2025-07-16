package tzjanosi.y2015.day09;

import java.util.ArrayList;
import java.util.List;

public class RoutePlanner {
    private List<String> visited = new ArrayList<>();
    private List<String> destinations = new ArrayList<>();
    private List<Way> ways = new ArrayList<>();
    private int distance;

    public RoutePlanner(RoutePlanner original) {
        visited = new ArrayList<>(original.visited);
        destinations = new ArrayList<>(original.destinations);
        ways = new ArrayList<>(original.ways);
        distance = original.distance;
    }

    public RoutePlanner(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            processLine(input.get(i));
        }
    }

    private void processLine(String s) {
        String[] parts = s.split(" = ");
        int distance = Integer.parseInt(parts[1]);
        String[] locations = parts[0].split(" to ");
        Way way = new Way(locations[0], locations[1], distance);
        ways.add(way);
        addToDestinations(locations[0], locations[1]);
    }

    private void addToDestinations(String location1, String location2) {
        addToDestinations(location1);
        addToDestinations(location2);
    }

    private void addToDestinations(String location) {
        if (!destinations.contains(location)) {
            destinations.add(location);
        }
    }

    public List<Way> getWays() {
        return ways;
    }

    public List<String> getDestinations() {
        return destinations;
    }

    public int getDistance() {
        return distance;
    }

    public void go(String destination) {
        if (!visited.isEmpty()) {
            Way way = findWay(visited.get(visited.size() - 1), destination);
            distance += way.getDistance();
            deleteWaysTo(visited.get(visited.size() - 1));
        }
        visited.add(destination);
        destinations.remove(destination);
    }

    private void deleteWaysTo(String destination) {
        List<Way> waysToDelete = ways.stream()
                .filter(w -> w.containsLocation(destination))
                .toList();
        ways.removeAll(waysToDelete);
    }

    private Way findWay(String from, String to) {
        return ways.stream()
                .filter(w -> w.containsLocations(from, to))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("No way found between %s and %s", from, to)));
    }
}
