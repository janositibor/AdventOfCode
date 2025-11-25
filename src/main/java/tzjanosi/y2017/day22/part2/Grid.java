package tzjanosi.y2017.day22.part2;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Grid {
    private Map<Coordinate, Node> nodes = new ConcurrentHashMap<>();
    private Virus virus;

    public Grid(List<String> input) {
        processInput(input);
    }

    public int steps(int count) {
        int counter = 0;
        while (counter < count) {
            Node node = findNode(virus.getPosition());
            virus.burst(node);
            counter++;
        }
        return virus.getInfectionCount();
    }

    private Node findNode(Coordinate position) {
        if (nodes.containsKey(position)) {
            return nodes.get(position);
        } else {
            Node node = new Node();
            nodes.put(position, node);
            return node;
        }
    }

    private void processInput(List<String> input) {
        buildVirus(input);
        for (int i = 0; i < input.size(); i++) {
            processLine(i, input.get(i));
        }
    }

    private void processLine(int y, String line) {
        char[] lineAsCharArray = line.toCharArray();
        for (int i = 0; i < lineAsCharArray.length; i++) {
            Coordinate position = new Coordinate(i, y);
            if (lineAsCharArray[i] == '#') {
                nodes.put(position, new Node(State.INFECTED));
            } else {
                nodes.put(position, new Node());
            }
        }

    }

    private void buildVirus(List<String> input) {
        Coordinate direction = new Coordinate(0, -1);
        Coordinate position = new Coordinate(input.get(0).length() / 2, input.size() / 2);
        virus = new Virus(position, direction);
    }
}
