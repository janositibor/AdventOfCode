package tzjanosi.y2018.day07;

import java.util.HashSet;
import java.util.Set;

public class Node implements Comparable<Node> {
    private char name;
    private Set<Character> preconditions = new HashSet<>();

    public Node(char name) {
        this.name = name;
    }

    public void addPreCondition(char preCondition) {
        preconditions.add(preCondition);
    }

    public void removePreCondition(char preCondition) {
        preconditions.remove(preCondition);
    }

    public boolean needPreCondition(char preCondition) {
        return preconditions.contains(preCondition);
    }

    public boolean isReady() {
        return preconditions.isEmpty();
    }


    @Override
    public int compareTo(Node other) {
        return name - other.name;
    }

    public char getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Node{" +
                "name=" + name +
                ", preconditions=" + preconditions +
                '}';
    }
}
