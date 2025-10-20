package tzjanosi.y2017.day12;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Plumber {
    private Map<Integer, List<Integer>> connections = new ConcurrentHashMap<>();
    private List<Integer> cluster = new ArrayList<>(List.of(0));

    public Plumber(List<String> input) {
        processInput(input);
    }

    private void processInput(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            processLine(input.get(i));
        }
    }

    private void processLine(String line) {
        String[] parts = line.split(" <-> ");
        Integer key = Integer.valueOf(parts[0]);
        List<Integer> value = getValues(parts[1]);
        connections.put(key, value);
    }

    private List<Integer> getValues(String part) {
        List<Integer> output = new ArrayList<>();
        String[] numbers = part.split(", ");
        for (int i = 0; i < numbers.length; i++) {
            output.add(Integer.valueOf(numbers[i]));
        }
        return output;
    }

    public int countCluster() {
        int i = 0;
        while (i < cluster.size()) {
            process(i);
            i++;
        }
        return i;
    }

    private void process(int index) {
        int key = cluster.get(index);
        List<Integer> numbersToAdd = connections.get(key);
        for (int i = 0; i < numbersToAdd.size(); i++) {
            int actual = numbersToAdd.get(i);
            if (!cluster.contains(actual)) {
                cluster.add(actual);
            }
        }
    }
}
