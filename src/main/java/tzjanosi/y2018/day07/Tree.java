package tzjanosi.y2018.day07;

import java.util.*;

public class Tree {
    private Queue<Node> ready = new PriorityQueue<>();
    private Set<Node> nodes = new TreeSet<>();

    public Tree(List<String> input) {
        processInput(input);
    }

    public int findTime(int numberOfAgents, int delay) {
        List<Agent> agents;
        List<Character> inProgress = new ArrayList<>();
        agents = createAgents(numberOfAgents, delay);
        int counter = 0;
        while (!nodes.isEmpty() || !ready.isEmpty() || !inProgress.isEmpty()) {
            moveNodesIntoReady();
            for (int i = 0; i < numberOfAgents; i++) {
                agentWorks(agents, i, counter, inProgress);
            }
            counter++;
        }
        return counter - 1;
    }

    private void agentWorks(List<Agent> agents, int i, int counter, List<Character> inProgress) {
        char nextStep;
        char charToRemove;
        Agent agent = agents.get(i);
        if (agent.isIdle(counter)) {
            charToRemove = agent.getWorkWith();
            agent.setWorkWith((char) 0);
            if (charToRemove > 0) {
                removeFromPreconditions(charToRemove);
                inProgress.remove((Character) charToRemove);
                moveNodesIntoReady();
            }
            if (!ready.isEmpty()) {
                nextStep = selectNextStep();
                agent.work(nextStep, counter);
                inProgress.add(nextStep);
            }
        }
    }

    private List<Agent> createAgents(int numberOfAgents, int delay) {
        List<Agent> result = new ArrayList<>();
        for (int i = 0; i < numberOfAgents; i++) {
            result.add(new Agent(delay));
        }
        return result;
    }

    public String findWay() {
        StringBuilder result = new StringBuilder();
        char nextStep;
        while (!nodes.isEmpty() || !ready.isEmpty()) {
            moveNodesIntoReady();
            nextStep = selectNextStep();
            result.append(nextStep);
            removeFromPreconditions(nextStep);
        }
        return result.toString();
    }

    private void removeFromPreconditions(char done) {
        nodes.stream()
                .filter(n -> n.needPreCondition(done))
                .forEach(n -> n.removePreCondition(done));
    }

    private char selectNextStep() {
        Node next = ready.poll();
        return next.getName();
    }

    private void moveNodesIntoReady() {
        List<Node> nodesToMove = nodes.stream().filter(Node::isReady).toList();
        ready.addAll(nodesToMove);
        nodes.removeAll(nodesToMove);
    }

    private void processInput(List<String> input) {
        for (String line : input) {
            processLine(line);
        }
    }

    private void processLine(String line) {
        char preCondition = line.charAt(5);
        char name = line.charAt(36);
        createNodeIfNotExist(preCondition);
        Node nameNode = findOrCreateNode(name);
        nameNode.addPreCondition(preCondition);
        nodes.add(nameNode);
    }

    private void createNodeIfNotExist(char name) {
        Optional<Node> found = nodes.stream().filter(n -> n.getName() == name).findFirst();
        if (found.isEmpty()) {
            nodes.add(new Node(name));
        }
    }

    private Node findOrCreateNode(char name) {
        Optional<Node> found = nodes.stream().filter(n -> n.getName() == name).findFirst();
        if (found.isPresent()) {
            return found.get();
        }
        return new Node(name);
    }

    public Set<Node> getNodes() {
        return nodes;
    }
}
