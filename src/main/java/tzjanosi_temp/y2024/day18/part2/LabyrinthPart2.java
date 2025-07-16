package tzjanosi_temp.y2024.day18.part2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LabyrinthPart2 {

    private List<Coordinate> walls = new ArrayList<>();
    private Set<Node> done = new HashSet<>();
    private Set<Node> inProgress = new HashSet<>();
    private Set<Node> next = new HashSet<>();
    private Coordinate start = new Coordinate(0, 0);
    private Coordinate end;
    private String lastBrick;

    public LabyrinthPart2(int size, List<String> input, int limit) {
        inProgress.add(new Node(start, 0));
        end = new Coordinate(size, size);
        createFrame(size);
        for (int i = 0; i < limit; i++) {
            readLine(input.get(i));
        }
        lastBrick = input.get(limit - 1);
    }

    private void createFrame(int size) {
        for (int i = -1; i <= size + 1; i++) {
            for (int j = -1; j <= size + 1; j++) {
                if (i == -1 || i == size + 1 || j == -1 || j == size + 1) {
                    walls.add(new Coordinate(i, j));
                }
            }
        }
    }

    public int calculateWay() {

        while (!inProgressContains(end)) {
            if (inProgress.isEmpty()) {
                return -1;
            }
            workWithInProgressList();
            rotateLists();
        }
        return getCostFromInProgressToCoordinate(end);
    }

    private void rotateLists() {
        done.addAll(inProgress);
        inProgress.clear();
        inProgress.addAll(next);
        next.clear();
    }

    private int getCostFromInProgressToCoordinate(Coordinate location) {
        return inProgress.stream()
                .filter(n -> n.getLocation().equals(location))
                .mapToInt(Node::getCost)
                .findFirst()
                .getAsInt();
    }

    private void workWithInProgressList() {
        for (Node node : inProgress) {
            workWithNode(node);
        }
    }


    private void workWithNode(Node node) {
        List<Node> neighbourNodes = findNeighbourNodes(node);
        addToNext(neighbourNodes);
    }

    private void addToNext(List<Node> nodes) {
        for (Node node : nodes) {
            if (isNew(node)) {
                next.add(node);
            }
        }
    }

    private boolean isNew(Node node) {
        return !(done.contains(node) || inProgress.contains(node) || next.contains(node));
    }

    public List<Node> findNeighbourNodes(Node node) {
        Coordinate location = node.getLocation();
        List<Node> output = new ArrayList<>();
        List<Coordinate> wayOuts = wayOut(location);
        Node nextNode;
        int cost = node.getCost() + 1;
        for (Coordinate to : wayOuts) {
            nextNode = new Node(to, cost);
            output.add(nextNode);
        }
        return output;
    }

    private boolean inProgressContains(Coordinate actualCoordinate) {
        return inProgress.stream().anyMatch(n -> n.getLocation().equals(actualCoordinate));
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

    private void readLine(String line) {
        String[] words = line.split(",");
        walls.add(new Coordinate(Integer.parseInt(words[0]), Integer.parseInt(words[1])));
    }

    public String getLastBrick() {
        return lastBrick;
    }

    public Coordinate getStart() {
        return start;
    }

    public Coordinate getEnd() {
        return end;
    }
}
