package tzjanosi.y2019.day06;

import java.util.*;

public class Service {
    private Set<Node> treesToBuild = new HashSet<>();

    public Service(List<String> input) {
        processInput(input);
    }

    public int findNumberOfJumpsToSanta() {
        return findNumberOfJumpsFromTo("YOU", "SAN");
    }

    private int findNumberOfJumpsFromTo(String from, String to) {
        Node jointAncestor = findJointAncestor(from, to);
        Node fromNode = findOrCreateNode(from);
        Node toNode = findOrCreateNode(to);
        return distanceFromAncestor(fromNode, jointAncestor) + distanceFromAncestor(toNode, jointAncestor);

    }

    private int distanceFromAncestor(Node descendant, Node ancestor) {
        int output = 0;
        Node actual = descendant.getParent();
        while (!actual.equals(ancestor)) {
            actual = actual.getParent();
            output++;
        }
        return output;
    }

    private Node findJointAncestor(String from, String to) {
        Node youNode = findOrCreateNode(from);
        Node actual = youNode.getParent();
        while (actual.findNodeById(to).isEmpty()) {
            actual = actual.getParent();
        }
        return actual;
    }

    public int findTotalNumberOfOrbits() {
        return treesToBuild.stream().filter(n -> "COM".equals(n.getId())).mapToInt(Node::getNumberOfOrbits).findFirst().getAsInt();
    }

    private Node findOrCreateNode(String id) {
        Optional<Node> optionalOutput = findNodeById(id);
        if (optionalOutput.isPresent()) {
            return optionalOutput.get();
        }
        return new Node(id);
    }

    private Optional<Node> findNodeById(String id) {
        for (Node actual : treesToBuild) {
            Optional<Node> optionalOutput = actual.findNodeById(id);
            if (optionalOutput.isPresent()) {
                return optionalOutput;
            }
        }
        return Optional.empty();
    }

    private void processInput(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            processLine(input.get(i));
        }
    }

    private void processLine(String line) {
        String[] ids = line.split("\\)");
        String parent = ids[0];
        String child = ids[1];
        processConnection(parent, child);
    }

    private void processConnection(String parentId, String childId) {
        Node parent = findOrCreateNode(parentId);
        Node child = findOrCreateNode(childId);
        if (parent.isNew()) {
            treesToBuild.add(parent);
        }
        parent.addChild(child);
        if (treesToBuild.contains(child)) {
            treesToBuild.remove(child);
        }
    }
}
