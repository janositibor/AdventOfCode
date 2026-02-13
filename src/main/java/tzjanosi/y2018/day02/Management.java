package tzjanosi.y2018.day02;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Management {
    private List<String> input;
    private int twos;
    private int threes;

    public Management(List<String> input) {
        this.input = input;
    }

    public String findSimilars() {
        for (int i = 0; i < input.size() - 1; i++) {
            for (int j = i + 1; j < input.size(); j++) {
                String word1 = input.get(i);
                String word2 = input.get(j);
                Optional<String> similars = areSimilars(word1, word2);
                if (similars.isPresent()) {
                    return similars.get();
                }
            }
        }
        throw new IllegalStateException("No similar titles found ...");
    }

    private Optional<String> areSimilars(String word1, String word2) {
        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();

        StringBuilder result = new StringBuilder();
        int numberOfDifferentChars = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (chars1[i] == chars2[i]) {
                result.append(chars1[i]);
            } else {
                numberOfDifferentChars++;
                if (numberOfDifferentChars > 1) {
                    return Optional.empty();
                }
            }
        }
        return Optional.of(result.toString());
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
