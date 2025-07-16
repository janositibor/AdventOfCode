package tzjanosi.y2024.day20.part1;

import java.util.*;

public class Labyrinth {
    private List<Coordinate> walls = new ArrayList<>();
    private Set<Node> done = new HashSet<>();
    private Set<Node> inProgress = new HashSet<>();
    private Set<Node> next = new HashSet<>();

    private Coordinate start;
    private Coordinate end;
    private int globalLimit = Integer.MAX_VALUE;
    private List<Coordinate> wayOutList;

    public Labyrinth(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            readLine(i, input.get(i));
        }
    }

    public Labyrinth() {
    }

    public int calculateWay() {
        inProgress.add(new Node(start, 0, null));
        while (!inProgressContains(end)) {
            if (inProgress.isEmpty()) {
                return Integer.MAX_VALUE;
            }
            if (!workWithInProgressList()) {
                return Integer.MAX_VALUE;
            }
            rotateLists();
        }
        return getCostFromInProgressToCoordinate(end);
    }

    private List<Coordinate> getWayFromInProgressToCoordinate(Coordinate location) {
        Node endNode = inProgress.stream()
                .filter(n -> n.getLocation().equals(location))
                .findFirst()
                .get();
        List<Coordinate> path = new ArrayList<>(List.of(endNode.getLocation()));
        Node parent = endNode.getParent();
        while (parent != null) {
            path.add(parent.getLocation());
            parent = parent.getParent();
        }
        Collections.reverse(path);
        return path;
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

    private boolean workWithInProgressList() {
        for (Node node : inProgress) {
            if (!workWithNode(node)) {
                return false;
            }
        }
        return true;
    }


    private boolean workWithNode(Node node) {
        if (node.getCost() > globalLimit) {
            return false;
        }
        List<Node> neighbourNodes = findNeighbourNodes(node);
        addToNext(neighbourNodes);
        return true;
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
            nextNode = new Node(to, cost, node);
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

    private void readLine(int i, String line) {
        char[] chars = line.toCharArray();
        for (int j = 0; j < chars.length; j++) {
            char sign = chars[j];
            if (sign != '.') {
                Coordinate coordinate = new Coordinate(j, i);
                switch (sign) {
                    case '#':
                        walls.add(coordinate);
                        break;
                    case 'E':
                        end = coordinate;
                        break;
                    case 'S':
                        start = coordinate;
                        break;
                    default:
                        throw new IllegalArgumentException(String.format("Unexpected input character: %c", sign));
                }
            }
        }
    }

    public Coordinate getStart() {
        return start;
    }

    public Coordinate getEnd() {
        return end;
    }

    public List<Coordinate> getWayOutList() {
        wayOutList = getWayFromInProgressToCoordinate(end);
        return wayOutList;
    }

    public List<Coordinate> getWalls() {
        return walls;
    }

    public void setWalls(List<Coordinate> walls) {
        this.walls = walls;
    }

    public void setStart(Coordinate start) {
        this.start = start;
    }

    public void setEnd(Coordinate end) {
        this.end = end;
    }

    public void setGlobalLimit(int globalLimit) {
        this.globalLimit = globalLimit;
    }
}
