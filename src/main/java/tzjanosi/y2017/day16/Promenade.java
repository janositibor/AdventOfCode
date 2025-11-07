package tzjanosi.y2017.day16;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Promenade {
    private int numberOfmembers;
    private Map<Integer, Integer> team = new ConcurrentHashMap<>();
    private List<Map.Entry<Character, List<Integer>>> operations = new ArrayList<>();

    public Promenade(int numberOfmembers, String input) {
        this.numberOfmembers = numberOfmembers;
        initTeam();
        processInput(input);
    }

    public void check() {
//        System.out.println(team);
        System.out.println(convertTeamToString());
        System.out.println();
        System.out.println(operations);
    }

    public String exec() {
        for (int i = 0; i < operations.size(); i++) {
            execCommand(operations.get(i));
//            check();
        }
        return convertTeamToString();
    }

    private void execCommand(Map.Entry<Character, List<Integer>> command) {
        switch (command.getKey()) {
            case 's':
                spin(command.getValue().get(0));
                break;
            case 'x':
                exchange(command.getValue().get(0), command.getValue().get(1));
                break;
            case 'p':
                partner(command.getValue().get(0), command.getValue().get(1));
                break;
            default:
                throw new IllegalArgumentException("No command found: " + command.getKey());
        }
    }


    private void spin(int shift) {
        Deque<Integer> puffer = new LinkedList<>();
        for (int i = 0; i < numberOfmembers; i++) {
            Map.Entry<Integer, Integer> entry1 = findEntryByKey(i);
            if (i - shift < 0) {
                Map.Entry<Integer, Integer> entry2 = findEntryByKey(i - shift + numberOfmembers);
                puffer.addLast(entry1.getValue());
                entry1.setValue(entry2.getValue());
            } else {
                puffer.addLast(entry1.getValue());
                entry1.setValue(puffer.removeFirst());

            }
        }
    }

    private void exchange(int key1, int key2) {
        Map.Entry<Integer, Integer> entry1 = findEntryByKey(key1);
        Map.Entry<Integer, Integer> entry2 = findEntryByKey(key2);
        swap(entry1, entry2);
    }

    private void partner(int value1, int value2) {
        Map.Entry<Integer, Integer> entry1 = findEntryByValue(value1);
        Map.Entry<Integer, Integer> entry2 = findEntryByValue(value2);
        swap(entry1, entry2);
    }

    private void swap(Map.Entry<Integer, Integer> entry1, Map.Entry<Integer, Integer> entry2) {
        int temp = entry1.getValue();
        entry1.setValue(entry2.getValue());
        entry2.setValue(temp);
    }

    private Map.Entry<Integer, Integer> findEntryByKey(int keyToFound) {
        return team.entrySet().stream()
                .filter(e -> e.getKey() == keyToFound)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No item found with key: " + keyToFound));
    }

    private Map.Entry<Integer, Integer> findEntryByValue(int valueToFound) {
        return team.entrySet().stream()
                .filter(e -> e.getValue() == valueToFound)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No item found with value: " + valueToFound));
    }

    private String convertTeamToString() {
        return team.entrySet().stream()
                .sorted(Comparator.comparingInt(Map.Entry::getKey))
//                .peek(e-> System.out.println((char) e.getValue().intValue()))
                .map(e -> String.valueOf((char) e.getValue().intValue()))
//                 .peek(s-> System.out.println(s))
                .collect(Collectors.joining());


    }

    private void initTeam() {
        int startingcode = 'a';
        for (int i = 0; i < numberOfmembers; i++) {
            team.put(i, startingcode + i);
        }
    }

    private void processInput(String input) {
        String[] words = input.split(",");
        for (int i = 0; i < words.length; i++) {
            processWord(words[i]);
        }
    }

    private void processWord(String word) {
        char sign = word.charAt(0);
        String remains = word.substring(1);
        List<Integer> parameters = findParametersFromInput(sign, remains);
        Map.Entry<Character, List<Integer>> map = Map.entry(sign, parameters);
        operations.add(map);
    }

    private List<Integer> findParametersFromInput(char sign, String remains) {
        List<Integer> output;
        switch (sign) {
            case 's':
                output = List.of(Integer.parseInt(remains), 0);
                break;
            case 'x':
                int positionOfSeparator = remains.indexOf('/');
                output = List.of(Integer.parseInt(remains.substring(0, positionOfSeparator)), Integer.parseInt(remains.substring(positionOfSeparator + 1)));
                break;
            case 'p':
                output = List.of((int) remains.charAt(0), (int) remains.charAt(2));
                break;
            default:
                throw new IllegalArgumentException("Unexpected sign: " + sign);
        }
        return output;
    }
}
