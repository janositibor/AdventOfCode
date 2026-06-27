package tzjanosi.y2019.day06;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Node {
    private String id;
    private Node parent;
    private List<Node> children = new ArrayList<>();
    private int numberOfOrbits;
    private int numberOfDescendants;

    public Node(String id) {
        this.id = id;
    }

    public boolean isNew() {
        return (parent == null && children.isEmpty());
    }

    public Optional<Node> findNodeById(String id) {
        if (this.id.equals(id)) {
            return Optional.of(this);
        }
        for (Node child : children) {
            Optional<Node> optionalOutput = child.findNodeById(id);
            if (optionalOutput.isPresent()) {
                return optionalOutput;
            }
        }
        return Optional.empty();
    }

    private void updateNumberOfOrbits() {
        numberOfOrbits = calculateNumberOfOrbits();
        if (parent != null) {
            parent.updateNumberOfOrbits();
        }
    }

    private int calculateNumberOfOrbits() {
        int output = 0;
        numberOfDescendants = 0;
        for (Node child : children) {
            numberOfDescendants += (child.numberOfDescendants + 1);
            output += child.getNumberOfOrbits() + child.numberOfDescendants + 1;
        }
        return output;
    }

    public void addChild(Node child) {
        child.parent = this;
        children.add(child);
        updateNumberOfOrbits();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node node = (Node) o;
        return Objects.equals(id, node.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public String getId() {
        return id;
    }

    public Node getParent() {
        return parent;
    }

    public int getNumberOfOrbits() {
        return numberOfOrbits;
    }


}
