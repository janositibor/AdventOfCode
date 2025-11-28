package tzjanosi.y2017.day24;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Node {
    private List<Magnet> bridge = new ArrayList<>();
    private int join;
    private Set<Magnet> magnets;

    public Node(int join, Set<Magnet> magnets) {
        this.join = join;
        this.magnets = magnets;
    }

    public Node(Node original) {
        bridge = deepCopyList(original.bridge);
        join = original.join;
        magnets = deepCopySet(original.magnets);
    }

    private Set<Magnet> deepCopySet(Set<Magnet> originals) {
        Set<Magnet> output = new HashSet<>();
        for (Magnet magnet : originals) {
            output.add(new Magnet(magnet));
        }
        return output;
    }

    private List<Magnet> deepCopyList(List<Magnet> originals) {
        List<Magnet> output = new ArrayList<>();
        for (int i = 0; i < originals.size(); i++) {
            output.add(new Magnet(originals.get(i)));
        }
        return output;
    }

    public int getStrength() {
        return bridge.stream().mapToInt(Magnet::getStrength).sum();
    }

    public boolean buildable() {
        return !findNextParts().isEmpty();
    }

    public Set<Node> createChildren() {
        Set<Node> output = new HashSet<>();
        Set<Magnet> nextParts = findNextParts();
        for (Magnet next : nextParts) {
            Node nextNode = new Node(this);
            nextNode.extendWith(next);
            output.add(nextNode);
        }
        return output;
    }

    private void extendWith(Magnet next) {
        join = next.getFreePin(join);
        bridge.add(next);
        magnets.remove(next);
    }

    private Set<Magnet> findNextParts() {
        return magnets.stream()
                .filter(m -> m.getPins().stream().anyMatch(d -> d == join))
                .collect(Collectors.toSet());
    }
}
