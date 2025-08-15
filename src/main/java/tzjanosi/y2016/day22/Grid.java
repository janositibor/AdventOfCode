package tzjanosi.y2016.day22;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    private List<Node> nodes = new ArrayList<>();

    public Grid(List<String> input) {
        for (int i = 2; i < input.size(); i++) {
            processLine(input.get(i));
        }
    }

    public int numberOfCompatiblePairs() {
        return nodes.stream().mapToInt(this::countOfOtherNodesToStore).sum();
    }

    private int countOfOtherNodesToStore(Node nodeFrom) {
        if (nodeFrom.getUsed() > 0) {
            return (int) nodes.stream().filter(n -> !n.equals(nodeFrom)).filter(n -> n.ableToStoreAdditional(nodeFrom.getUsed())).count();
        }
        return 0;
    }

    private void processLine(String line) {
        String[] words = line.split(" +");
        Coordinate coordinate = createCoordinate(words[0]);
        int size = extractIntFrom(words[1]);
        int used = extractIntFrom(words[2]);
        int available = extractIntFrom(words[3]);

        nodes.add(new Node(coordinate, size, used, available));
    }

    private int extractIntFrom(String input) {
        return Integer.parseInt(input.substring(0, input.length() - 1));
    }

    private Coordinate createCoordinate(String input) {
        String[] parts = input.split("-");
        return new Coordinate(Integer.parseInt(parts[1].substring(1)), Integer.parseInt(parts[2].substring(1)));
    }

    public List<Node> getNodes() {
        return nodes;
    }
}
