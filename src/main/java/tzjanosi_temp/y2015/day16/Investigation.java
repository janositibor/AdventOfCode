package tzjanosi_temp.y2015.day16;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

public class Investigation {
    private List<Sue> sues = new ArrayList<>();
    private Map<String, Optional<Integer>> criteria = new ConcurrentHashMap<>();

    public Investigation(List<String> input) {
        createCriteria();
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            processLine(line);
        }
    }

    public int filterPart1() {
        return filter(s -> s.fulfill(criteria));
    }

    public int filterPart2() {
        return filter(s -> s.fulfillPart2(criteria));
    }

    private int filter(Predicate<Sue> condition) {
        return sues.stream()
                .filter(condition::test)
                .mapToInt(Sue::getId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("No Sue found with these criteria: %s", criteria)));
    }

    private void processLine(String line) {
        String[] words = line.split(" ");
        Sue sue = createSue(words);
        sues.add(sue);
    }

    private Sue createSue(String[] words) {
        int id = Integer.parseInt(trimLastCharacter(words[1]));
        int numberOfKnownProperties = (words.length - 2) / 2;
        Map<String, Optional<Integer>> knownProperties = new ConcurrentHashMap<>();
        for (int i = 0; i < numberOfKnownProperties; i++) {
            int index = 2 + 2 * i;
            String name = trimLastCharacter(words[index]);
            String stringValue = (i == numberOfKnownProperties - 1 ? words[index + 1] : trimLastCharacter(words[index + 1]));
            Optional<Integer> value = Optional.of(Integer.parseInt(stringValue));
            knownProperties.put(name, value);
        }
        return new Sue(id, knownProperties);
    }

    private String trimLastCharacter(String word) {
        return word.substring(0, word.length() - 1);
    }

    private void createCriteria() {
        criteria.put("children", Optional.of(3));
        criteria.put("cats", Optional.of(7));
        criteria.put("samoyeds", Optional.of(2));
        criteria.put("pomeranians", Optional.of(3));
        criteria.put("akitas", Optional.of(0));
        criteria.put("vizslas", Optional.of(0));
        criteria.put("goldfish", Optional.of(5));
        criteria.put("trees", Optional.of(3));
        criteria.put("cars", Optional.of(2));
        criteria.put("perfumes", Optional.of(1));
    }

    public List<Sue> getSues() {
        return sues;
    }

    public Map<String, Optional<Integer>> getCriteria() {
        return criteria;
    }

    @Override
    public String toString() {
        return "Investigation{" +
                "sues=" + sues +
                ", criteria=" + criteria +
                '}';
    }
}
