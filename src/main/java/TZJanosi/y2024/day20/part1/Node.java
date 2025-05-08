package TZJanosi.y2024.day20.part1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node {
    private Coordinate location;
    private int cost;
    private Node parent;

    public Node(Coordinate location, int cost, Node parent) {
        this.location = location;
        this.cost = cost;
        this.parent = parent;
    }

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

    public Node getParent() {
        return parent;
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
