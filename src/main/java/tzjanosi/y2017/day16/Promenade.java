package tzjanosi.y2017.day16;

import java.util.*;
import java.util.stream.Collectors;

public class Promenade {
    private int numberOfMembers;
    private List<Integer> team = new ArrayList<>();
    private List<Map.Entry<Character, List<Integer>>> operations = new ArrayList<>();

    public Promenade(int numberOfmembers, String input) {
        this.numberOfMembers = numberOfmembers;
        initTeam();
        processInput(input);
    }

    public int findPeriod(int count) {
        for (int i = 0; i < count; i++) {
            exec();
            if ("abcdefghijklmnop".equals(convertTeamToString())) {
                return i + 1;
            }
        }
        return -1;
    }

    public String repeatedLenientExec(int cycles) {
        int effective = cycles % findPeriod(10_000);
        for (int i = 0; i < effective; i++) {
            exec();
        }
        return convertTeamToString();
    }


    public String repeatedExec(int cycles) {
        for (int i = 0; i < cycles; i++) {
            exec();
        }
        return convertTeamToString();
    }

    public String exec() {
        for (int i = 0; i < operations.size(); i++) {
            execCommand(operations.get(i));
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
        Collections.rotate(team, shift);
    }

    private void exchange(int key1, int key2) {
        int temp = team.get(key1);
        team.set(key1, team.get(key2));
        team.set(key2, temp);
    }

    private void partner(int value1, int value2) {
        int key1 = team.indexOf(value1);
        int key2 = team.indexOf(value2);
        team.set(key1, value2);
        team.set(key2, value1);
    }

    private String convertTeamToString() {
        return team.stream()
                .map(d -> String.valueOf((char) d.intValue()))
                .collect(Collectors.joining());
    }

    private void initTeam() {
        int startingcode = 'a';
        for (int i = 0; i < numberOfMembers; i++) {
            team.add(startingcode + i);
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
