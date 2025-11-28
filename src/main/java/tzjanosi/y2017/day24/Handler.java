package tzjanosi.y2017.day24;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Handler {
    private Set<Node> bridges = new HashSet<>();
    private int maxStrength;

    public Handler(List<String> input) {
        processInput(input);
    }

    public int build() {
        while (!bridges.isEmpty()) {
            Node buildable = selectABuildableNode();
            Set<Node> children = buildable.createChildren();
            bridges.remove(buildable);
            putBackBuildableChildren(children);
        }
        return maxStrength;
    }

    private void putBackBuildableChildren(Set<Node> children) {
        for (Node child : children) {
            if (child.buildable()) {
                bridges.add(child);
            } else {
                maxStrength = Math.max(maxStrength, child.getStrength());
            }
        }
    }

    private Node selectABuildableNode() {
        return bridges.stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No buildable bridge found!"));
    }

    private void processInput(List<String> input) {
        Set<Magnet> originalMagnets = new HashSet<>();
        for (int i = 0; i < input.size(); i++) {
            Magnet magnet = processLine(input.get(i));
            originalMagnets.add(magnet);
        }
        Node startNode = new Node(0, originalMagnets);
        bridges.add(startNode);
    }

    private Magnet processLine(String line) {
        String[] pinsAsStrings = line.split("/");
        return new Magnet(Integer.parseInt(pinsAsStrings[0]), Integer.parseInt(pinsAsStrings[1]));
    }
}
