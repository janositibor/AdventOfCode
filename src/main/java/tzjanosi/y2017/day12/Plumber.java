package tzjanosi.y2017.day12;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Plumber {
    private Map<Integer, List<Integer>> connections = new ConcurrentHashMap<>();
    private List<Integer> clusterZero = new ArrayList<>(List.of(0));
    private List<Set<Integer>> clusters = new ArrayList<>();

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

    public int countClusters() {
        for (Map.Entry<Integer, List<Integer>> entry : connections.entrySet()) {
            Set<Integer> numbers = new HashSet<>(entry.getValue());
            numbers.add(entry.getKey());
            addToClusters(numbers);
        }
        mergeClusters();
        return clusters.size();
    }

    private void addToClusters(Set<Integer> numbersToAdd) {
        for (Set<Integer> actualCluster : clusters) {
            if (!distinct(actualCluster, numbersToAdd)) {
                actualCluster.addAll(numbersToAdd);
                return;
            }
        }
        clusters.add(numbersToAdd);
    }

    private boolean distinct(Set<Integer> group1, Set<Integer> group2) {
        return group2.stream().noneMatch(group1::contains);
    }

    public int countClusterZero() {
        int i = 0;
        while (i < clusterZero.size()) {
            process(i);
            i++;
        }
        return i;
    }

    private void mergeClusters() {
        boolean worthContinue = true;
        List<Set<Integer>> elementToRemove = new ArrayList<>();
        while (worthContinue) {
            worthContinue = false;
            for (int i = 0; i < clusters.size(); i++) {
                Set<Integer> base = clusters.get(i);
                for (int j = i + 1; j < clusters.size(); j++) {
                    Set<Integer> actual = clusters.get(j);
                    if (!distinct(base, actual)) {
                        worthContinue = true;
                        base.addAll(actual);
                        elementToRemove.add(actual);
                    }
                }
                clusters.removeAll(elementToRemove);
            }
        }
    }

    private void process(int index) {
        int key = clusterZero.get(index);
        List<Integer> numbersToAdd = connections.get(key);
        for (int i = 0; i < numbersToAdd.size(); i++) {
            int actual = numbersToAdd.get(i);
            if (!clusterZero.contains(actual)) {
                clusterZero.add(actual);
            }
        }
    }
}
