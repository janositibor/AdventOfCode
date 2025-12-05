package tzjanosi.y2025.day05;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cafeteria {
    private Set<Long> ingredients = new HashSet<>();
    private Set<Range> ranges = new HashSet<>();

    public Cafeteria(List<String> input) {
        processInput(input);
    }

    public int numberOfIngredientsInRanges() {
        return (int) ingredients.stream()
                .filter(d -> ranges.stream().anyMatch(r -> r.isInTheRange(d)))
                .count();
    }

    private void processInput(List<String> input) {
        int indexOfSeparatorLine = input.indexOf("");
        processRanges(input.subList(0, indexOfSeparatorLine));
        processIngredients(input.subList(indexOfSeparatorLine + 1, input.size()));
    }

    private void processIngredients(List<String> lines) {
        for (String line : lines) {
            ingredients.add(Long.parseLong(line));
        }
    }

    private void processRanges(List<String> lines) {
        for (String line : lines) {
            String[] words = line.split("-");
            Range range = new Range(Long.parseLong(words[0]), Long.parseLong(words[1]));
            ranges.add(range);
        }
    }
}
