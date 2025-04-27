package TZJanosi.y2024.day18;

import java.util.Objects;

public class Node {
    private Coordinate location;
    private int cost;

    public Node(Coordinate location, int cost) {
        this.location = location;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }


    public Coordinate getLocation() {
        return location;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(location, node.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location);
    }

    @Override
    public String toString() {
        return "Node{" +
                "location=" + location +
                ", cost=" + cost +
                '}';
    }
}
