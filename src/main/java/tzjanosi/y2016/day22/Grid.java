package tzjanosi.y2016.day22;

import java.util.*;

public class Grid {
    private List<Node> nodes = new ArrayList<>();
    private Coordinate limit = new Coordinate(0, 0);
    private List<Coordinate> walls = new ArrayList<>();
    private Set<Coordinate> alreadyVisited = new HashSet<>();
    private Queue<NodeForPathFinder> way = new ArrayDeque<>();

    public Grid(List<String> input) {
        for (int i = 2; i < input.size(); i++) {
            processLine(input.get(i));
        }
        createWalls();
    }

    public int calculateTotalNumberToMoveData() {
        int moveEmptySlotIntoInitialPosition = moveEmptySlotIntoInitialPosition();
        int stepNumberToMoveData = stepNumberToMoveData();
        return moveEmptySlotIntoInitialPosition + 1 + stepNumberToMoveData;
    }

    private int stepNumberToMoveData() {
        if (countWalls(0) > 0 || countWalls(1) > 0) {
            throw new IllegalStateException(String.format("Wall detected in the first or in the second row!\nThe waals:%s", walls.toString()));
        }
        return 5 * (limit.getX() - 1);
    }

    private int moveEmptySlotIntoInitialPosition() {
        if (numberOfEmptyNodes() > 1) {
            throw new IllegalStateException(String.format("More than one empty slot detected!"));
        }
        Coordinate startCoordinate = findEmptyNode().getCoordinate();
        Coordinate end = new Coordinate(limit.getX() - 1, 0);
        if (startCoordinate.equals(end)) {
            return 0;
        }
        NodeForPathFinder start = new NodeForPathFinder(startCoordinate, 0);
        addCoordinate(start);
        return calculateShortestWay(end);
    }

    private void addCoordinate(NodeForPathFinder actual) {
        way.add(actual);
        alreadyVisited.add(actual.getCoordinate());
    }

    private int calculateShortestWay(Coordinate end) {
        while (!way.isEmpty()) {
            NodeForPathFinder actual = way.remove();
            int actualSteps = actual.getStep();
            List<Coordinate> neighbours = actual.getCoordinate().getNeighbours(limit);
            for (Coordinate neighbourCoordinate : neighbours) {
                if (neighbourCoordinate.equals(end)) {
                    return actualSteps + 1;
                }
                if (!alreadyVisited.contains(neighbourCoordinate) && !walls.contains(neighbourCoordinate)) {
                    NodeForPathFinder next = new NodeForPathFinder(neighbourCoordinate, actualSteps + 1);
                    addCoordinate(next);
                }
            }
        }
        throw new IllegalStateException(String.format("The end coordinate (%s) not accessible!", end.toString()));
    }

    public int countWalls(int row) {
        Node empty = findEmptyNode();
        return (int) nodes.stream()
                .filter(n -> n.getUsed() > empty.getSize())
                .filter(n -> n.getCoordinate().getY() == row)
                .count();
    }

    private void createWalls() {
        Node empty = findEmptyNode();
        walls = nodes.stream()
                .filter(n -> n.getUsed() > empty.getSize())
                .map(Node::getCoordinate)
                .toList();
    }

    public int numberOfCompatiblePairs() {
        return nodes.stream().mapToInt(this::countOfOtherNodesToStore).sum();
    }

    private Node findEmptyNode() {
        return nodes.stream()
                .filter(n -> n.getUsed() == 0)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No empty node found!"));
    }

    private int numberOfEmptyNodes() {
        return (int) nodes.stream()
                .filter(n -> n.getUsed() == 0)
                .count();
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
        createLimit(coordinate);
        int size = extractIntFrom(words[1]);
        int used = extractIntFrom(words[2]);
        int available = extractIntFrom(words[3]);

        nodes.add(new Node(coordinate, size, used, available));
    }

    private void createLimit(Coordinate coordinate) {
        if (coordinate.getX() > limit.getX()) {
            limit.setX(coordinate.getX());
        }
        if (coordinate.getY() > limit.getY()) {
            limit.setY(coordinate.getY());
        }
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

    public Coordinate getLimit() {
        return limit;
    }

    public List<Coordinate> getWalls() {
        return walls;
    }
}
