package tzjanosi.y2018.day08;

import java.util.ArrayDeque;
import java.util.Queue;

public class Tree {
    private Node root;
    private Queue<Integer> numbers = new ArrayDeque<>();

    public Tree(String input) {
        processInput(input);
    }

    public int sumOfMetaData() {
        return root.sumOfMetaData();
    }

    private void processInput(String input) {
        String[] numbersInArray = input.split(" ");
        for (int i = 0; i < numbersInArray.length; i++) {
            numbers.add(Integer.valueOf(numbersInArray[i]));
        }
        root = readNode();
    }

    private Node readNode() {
        Node result;
        int numberOfChildren = numbers.poll();
        int numberOfMetaData = numbers.poll();
        result = new Node();
        for (int i = 0; i < numberOfChildren; i++) {
            result.addChild(readNode());
        }
        for (int i = 0; i < numberOfMetaData; i++) {
            result.addMeta(numbers.poll());
        }
        return result;
    }
}
