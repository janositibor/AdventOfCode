package TZJanosi.y2024.day16.part1;

import java.util.Objects;

public class Node {
    private Coordinate location;
    private Coordinate incomingDirection;
    private int cost;
    private int costToEnd;

    public Node(Coordinate location, Coordinate incomingDirection) {
        this.location = location;
        this.incomingDirection = incomingDirection;
    }
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Coordinate getLocation() {
        return location;
    }

    public Coordinate getIncomingDirection() {
        return incomingDirection;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(location, node.location) && Objects.equals(incomingDirection, node.incomingDirection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, incomingDirection);
    }

    @Override
    public String toString() {
        return "Node{" +
                "location=" + location +
                ", incomingDirection=" + incomingDirection +
                ", cost=" + cost +
                ", costToEnd=" + costToEnd +
                '}';
    }
}
