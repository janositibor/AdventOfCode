package tzjanosi.y2018.day02;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Management {
    private List<String> input;
    private int twos;
    private int threes;

    public Management(List<String> input) {
        this.input = input;
    }

    public int manage() {
        for (int i = 0; i < input.size(); i++) {
            String word = input.get(i);
            Map<Character, Long> splitted = splitWord(word);
            if (splitted.containsValue(2L)) {
                twos++;
            }
            if (splitted.containsValue(3L)) {
                threes++;
            }
        }
        return twos * threes;
    }

    private Map<Character, Long> splitWord(String word) {
        return word.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(c -> c, Collectors.counting()));
    }
}
