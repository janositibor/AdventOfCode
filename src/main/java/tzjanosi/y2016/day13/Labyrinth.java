package tzjanosi.y2016.day13;

import java.util.*;

public class Labyrinth {
    private List<Coordinate> walls = new ArrayList<>();
    private Set<Node> done = new HashSet<>();
    private Set<Node> inProgress = new HashSet<>();
    private Set<Node> next = new HashSet<>();
    private Coordinate start = new Coordinate(1, 1);
    private Coordinate end;
    private Coordinate size;
    private int favoriteNumber;

    public Labyrinth(Coordinate size, Coordinate end, int favoriteNumber) {
        inProgress.add(new Node(start, 0));
        this.end = end;
        this.favoriteNumber = favoriteNumber;
        this.size = size;
        createFrame();
        createWalls();
    }

    private void createFrame() {
        for (int i = -1; i <= size.getX() + 1; i++) {
            for (int j = -1; j <= size.getY() + 1; j++) {
                if (i == -1 || i == size.getX() + 1 || j == -1 || j == size.getY() + 1) {
                    walls.add(new Coordinate(i, j));
                }
            }
        }
    }

    public int calculateWay() {
        while (!inProgressContains(end)) {
            if (inProgress.isEmpty()) {
                return Integer.MAX_VALUE;
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

    private void createWalls() {
        for (int i = 0; i <= size.getX(); i++) {
            for (int j = 0; j <= size.getY(); j++) {
                Coordinate actualCoordinate = new Coordinate(i, j);
                if (isWall(actualCoordinate)) {
                    walls.add(actualCoordinate);
                }
            }
        }
    }

    private boolean isWall(Coordinate coordinate) {
        int x = coordinate.getX();
        int y = coordinate.getY();
        int parameter1 = x * x + 3 * x + 2 * x * y + y + y * y;
        int parameter2 = parameter1 + favoriteNumber;
        String binaryRepresentation = Integer.toBinaryString(parameter2);
        long countOfOnes = binaryRepresentation.chars()
                .filter(c -> c == '1')
                .count();
        return countOfOnes % 2 == 1;
    }

    public Coordinate getStart() {
        return start;
    }

    public Coordinate getEnd() {
        return end;
    }
}
