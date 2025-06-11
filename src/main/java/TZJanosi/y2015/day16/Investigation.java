package TZJanosi.y2015.day16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Investigation {
    private List<Sue> sues = new ArrayList<>();
    private Map<String, Integer> criteria = new HashMap<>();

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
                .filter(s -> condition.test(s))
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
        Map<String, Integer> knownProperties = new HashMap<>();
        for (int i = 0; i < numberOfKnownProperties; i++) {
            int index = 2 + 2 * i;
            String name = trimLastCharacter(words[index]);
            String stringValue = (i == numberOfKnownProperties - 1 ? words[index + 1] : trimLastCharacter(words[index + 1]));
            Integer value = Integer.parseInt(stringValue);
            knownProperties.put(name, value);
        }
        Sue sue = new Sue(id, knownProperties);
        return sue;
    }

    private String trimLastCharacter(String word) {
        return word.substring(0, word.length() - 1);
    }

    private void createCriteria() {
        criteria.put("children", 3);
        criteria.put("cats", 7);
        criteria.put("samoyeds", 2);
        criteria.put("pomeranians", 3);
        criteria.put("akitas", 0);
        criteria.put("vizslas", 0);
        criteria.put("goldfish", 5);
        criteria.put("trees", 3);
        criteria.put("cars", 2);
        criteria.put("perfumes", 1);
    }

    public List<Sue> getSues() {
        return sues;
    }

    public Map<String, Integer> getCriteria() {
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
