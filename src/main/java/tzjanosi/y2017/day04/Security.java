package tzjanosi.y2017.day04;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Security {
    private List<String> phrases;

    public Security(List<String> phrases) {
        this.phrases = phrases;
    }

    public int numberOfValidPhrases() {
        return (int) phrases.stream()
                .filter(this::isValid)
                .count();
    }

    private boolean isValid(String phrase) {
        String[] words = phrase.split(" ");
        Map<String, Long> map = Arrays.stream(words).collect(Collectors.groupingBy(w -> w, Collectors.counting()));
        return map.values().stream().mapToInt(Long::intValue).max().getAsInt() < 2;
    }
}
