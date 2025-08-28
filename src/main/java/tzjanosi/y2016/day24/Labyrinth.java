package tzjanosi.y2016.day24;

import com.google.common.collect.Collections2;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Labyrinth {
    private List<Coordinate> walls = new ArrayList<>();
    private Coordinate[] targets;
    private Queue<Node> nodes;
    private Set<Coordinate> alreadyVisited;
    private int[][] minimalDistances;
    private Map<Integer, Coordinate> temp = new ConcurrentHashMap<>();

    public Labyrinth(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            readLine(i, input.get(i));
        }
        createTargets();
        calculateMinimalDistances();
    }

    public int calculateWayPart2() {
        int output = Integer.MAX_VALUE;
        for (List<Integer> perm : createPermutations(createList(numberOfTargets() - 1))) {
            int routeLength = calculateRouteLengthPart2(perm);
            if (routeLength < output) {
                output = routeLength;
            }
        }
        return output;
    }

    public int calculateWay() {
        int output = Integer.MAX_VALUE;
        for (List<Integer> perm : createPermutations(createList(numberOfTargets() - 1))) {
            int routeLength = calculateRouteLength(perm);
            if (routeLength < output) {
                output = routeLength;
            }
        }
        return output;
    }

    private int calculateRouteLengthPart2(List<Integer> input) {
        int output = 0;
        int from = 0;
        for (int i = 0; i < input.size(); i++) {
            int to = input.get(i);
            output += minimalDistances[from][to];
            from = to;
        }
        output += minimalDistances[from][0];

        return output;
    }

    private int calculateRouteLength(List<Integer> input) {
        int output = 0;
        int from = 0;
        for (int i = 0; i < input.size(); i++) {
            int to = input.get(i);
            output += minimalDistances[from][to];
            from = to;
        }
        return output;
    }

    private void calculateMinimalDistances() {
        minimalDistances = new int[targets.length][targets.length];
        for (int i = 0; i < targets.length; i++) {
            for (int j = i + 1; j < targets.length; j++) {
                Coordinate start = targets[i];
                Coordinate end = targets[j];
                int minimalDistance = calculateMinimalDistance(start, end);
                minimalDistances[i][j] = minimalDistance;
                minimalDistances[j][i] = minimalDistance;
            }
        }
    }

    private int calculateMinimalDistance(Coordinate start, Coordinate end) {
        int output = -1;
        nodes = new ArrayDeque<>();
        alreadyVisited = new HashSet<>();

        Node startNode = new Node(start, 0);
        nodes.add(startNode);
        alreadyVisited.add(start);
        while (output == -1) {
            Node actual = nodes.remove();
            output = processNode(actual, end);
        }
        return output;
    }

    private int processNode(Node actual, Coordinate end) {
        Coordinate location = actual.getLocation();
        List<Coordinate> wayOuts = wayOut(location);
        for (Coordinate to : wayOuts) {
            if (to.equals(end)) {
                return actual.getCost() + 1;
            }
            if (!alreadyVisited.contains(to)) {
                Node nextNode = new Node(to, actual.getCost() + 1);
                nodes.add(nextNode);
                alreadyVisited.add(to);
            }
        }
        return -1;
    }

    private List<Coordinate> wayOut(Coordinate location) {
        List<Coordinate> output = new ArrayList<>();
        Coordinate neighbour;
        for (int i = -1; i <= 1; i += 2) {
            neighbour = new Coordinate(location.getX() + i, location.getY());
            if (!walls.contains(neighbour)) {
                output.add(neighbour);
            }
        }
        for (int j = -1; j <= 1; j += 2) {
            neighbour = new Coordinate(location.getX(), location.getY() + j);
            if (!walls.contains(neighbour)) {
                output.add(neighbour);
            }
        }
        return output;
    }

    private Collection<List<Integer>> createPermutations(List<Integer> input) {
        return Collections2.permutations(input);
    }

    private List<Integer> createList(int n) {
        return IntStream.rangeClosed(1, n)
                .boxed()
                .collect(Collectors.toList());
    }

    private int numberOfTargets() {
        return temp.keySet().stream()
                .mapToInt(x -> x)
                .max()
                .orElseThrow(() -> new IllegalStateException("Empty input map"))
                + 1;
    }

    private void createTargets() {
        int max = numberOfTargets();
        targets = new Coordinate[max];
        for (int i = 0; i < max; i++) {
            targets[i] = temp.get(i);
        }
    }

    private void readLine(int i, String line) {
        char[] chars = line.toCharArray();
        for (int j = 0; j < chars.length; j++) {
            Coordinate coordinate = new Coordinate(j, i);
            char sign = chars[j];
            if (Character.isDigit(sign)) {
                int index = sign - '0';
                temp.put(index, coordinate);
            }
            if (sign == '#') {
                walls.add(coordinate);
            }
        }
    }
}
