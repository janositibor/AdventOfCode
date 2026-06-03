package tzjanosi.y2018.day20;

import java.util.*;
import java.util.stream.Collectors;

public class Node {
    private Node parent;
    private List<Node> children = new ArrayList<>();
    private String prefix = "";
    private List<Way> ways = new ArrayList<>(List.of(new Way("")));

    public void extend(Map<Coordinate, Integer> lengthMap) {
        for (int i = 0; i < ways.size(); i++) {
            fillLengthMap(lengthMap, ways.get(i), prefix);
            ways.get(i).extendRoute(prefix);
        }
        prefix = "";
    }

    public void buildWays(Map<Coordinate, Integer> lengthMap) {

        List<Way> updatedWays = new ArrayList<>();
        for (int j = 0; j < children.size(); j++) {
            Node child = children.get(j);
            updatedWays.addAll(child.getWays());
        }
        List<Way> filteredUpdatedWays = filterWays(updatedWays);
        ways = filteredUpdatedWays;
        children = new ArrayList<>();
    }

    private void fillLengthMap(Map<Coordinate, Integer> lengthMap, Way from, String route) {
        Coordinate position = from.getPosition();
        Coordinate direction;
        int actualLength = from.routeLength();
        for (int i = 0; i < route.length(); i++) {
            direction = directionFromLetter(route.charAt(i));
            actualLength++;
            position = position.shift(direction);
            checkAndUpdateLengthMap(lengthMap, position, actualLength);
        }
    }

    private void checkAndUpdateLengthMap(Map<Coordinate, Integer> lengthMap, Coordinate position, int actualLength) {
        if (!lengthMap.keySet().contains(position)) {
            lengthMap.put(position, Integer.MAX_VALUE);
        }
        if (lengthMap.get(position) > actualLength) {
            lengthMap.put(position, actualLength);
        }
    }

    private Coordinate directionFromLetter(char c) {
        Coordinate output;
        switch (c) {
            case 'N':
                output = new Coordinate(0, -1);
                break;
            case 'S':
                output = new Coordinate(0, 1);
                break;
            case 'E':
                output = new Coordinate(1, 0);
                break;
            case 'W':
                output = new Coordinate(-1, 0);
                break;
            default:
                throw new IllegalArgumentException("Unexpected letter: " + c);
        }
        return output;
    }

    private List<Way> filterWays(List<Way> waysToFilter) {
        Map<Coordinate, Integer> map = createFilterMapToWays(waysToFilter);

        return waysToFilter.stream().filter(w -> w.routeLength() == map.get(w.getPosition())).toList();

    }

    private Map<Coordinate, Integer> createFilterMapToWays(List<Way> waysToFilter) {
        return waysToFilter.stream().collect(Collectors.groupingBy(Way::getPosition, Collectors.collectingAndThen(
                Collectors.mapping(
                        Way::routeLength,
                        Collectors.minBy(Comparator.naturalOrder())
                ),
                Optional::get
        )));
    }

    public Node addChild() {
        Node child = new Node();
        child.parent = this;
        child.ways = ways.stream().map(Way::new).toList();
        children.add(child);
        return child;
    }

    public Node addSibling() {
        Node parent = this.parent;
        return parent.addChild();
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Node getParent() {
        return parent;
    }

    @Override
    public String toString() {
        return "Node{" +
                "ways=" + ways +
                '}';
    }

    public List<String> getRoutes() {
        return ways.stream().map(Way::getRoute).toList();
    }

    public List<Way> getWays() {
        return ways;
    }
}
