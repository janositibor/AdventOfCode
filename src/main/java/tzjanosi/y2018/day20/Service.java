package tzjanosi.y2018.day20;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Service {
    private Node root = new Node();
    private int originalLength = -1;
    private Map<Coordinate, Integer> lengthMap = new ConcurrentHashMap<>();

    public int processPart2(String input) {
        root = new Node();
        buildPaths(root, input);
        return checkRoomsAtLeastSteps(1000);
    }

    private int checkRoomsAtLeastSteps(int limit) {
        return (int) lengthMap.entrySet().stream().filter(e -> e.getValue() >= limit).count();
    }

    public int process(String input) {
        root = new Node();
        buildPaths(root, input);
        return check();
    }

    private int check() {
        return lengthMap.entrySet().stream().mapToInt(Map.Entry::getValue).max().getAsInt();
    }

    private void buildPaths(Node actual, String input) {
        if (originalLength == -1) {
            originalLength = input.length();
        }
        String temp;
        if (input.charAt(0) == '^') {
            temp = input.substring(1);
        } else {
            temp = input;
        }
        int index = 0;
        while (isDirection(temp.charAt(index))) {
            index++;
        }
        String append = temp.substring(0, index);
        String remaining = temp.substring(index + 1);
        actual.setPrefix(append);
        if (index > 0) {
            actual.extend(lengthMap);
        }
        Node next = actual;
        if (temp.charAt(index) == '(') {
            next = actual.addChild();
        } else if (temp.charAt(index) == '|') {
            next = actual.addSibling();
        } else if (temp.charAt(index) == ')') {
            next = actual.getParent();
            next.buildWays(lengthMap);
        }
        if (!remaining.isEmpty()) {
            buildPaths(next, remaining);
        }
    }


    private boolean isDirection(char character) {
        return character == 'N' || character == 'E' || character == 'S' || character == 'W';
    }


    public Node getRoot() {
        return root;
    }
}
