package tzjanosi.y2016.day22;

import java.util.Objects;

public class Node {
    private Coordinate coordinate;
    private int size;
    private int used;
    private int available;

    public Node(Coordinate coordinate, int size, int used, int available) {
        this.coordinate = coordinate;
        this.size = size;
        this.used = used;
        this.available = available;
    }

    public boolean ableToStoreAdditional(int amount) {
        return amount < available;
    }

    public int getUsed() {
        return used;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node node = (Node) o;
        return Objects.equals(coordinate, node.coordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(coordinate);
    }

    @Override
    public String toString() {
        return "Node{" +
                "coordinate=" + coordinate +
                ", size=" + size +
                ", used=" + used +
                ", available=" + available +
                '}';
    }
}
