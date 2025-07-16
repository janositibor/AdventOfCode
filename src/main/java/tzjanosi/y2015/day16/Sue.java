package tzjanosi.y2015.day16;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class Sue {
    private int id;
    private Map<String, Optional<Integer>> properties = new ConcurrentHashMap<>();

    public Sue(int id, Map<String, Optional<Integer>> knownProperties) {
        this.id = id;
        createNullProperties();
        properties.putAll(knownProperties);
    }

    public boolean fulfill(Map<String, Optional<Integer>> criteria) {
        for (Map.Entry<String, Optional<Integer>> entry : properties.entrySet()) {
            if (entry.getValue().isPresent()) {
                String key = entry.getKey();
                if (!entry.getValue().equals(criteria.get(key))) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean fulfillPart2(Map<String, Optional<Integer>> criteria) {
        for (Map.Entry<String, Optional<Integer>> entry : properties.entrySet()) {
            if (entry.getValue().isPresent()) {
                String key = entry.getKey();
                if (!valid(entry, criteria, key)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean valid(Map.Entry<String, Optional<Integer>> entry, Map<String, Optional<Integer>> criteria, String key) {
        switch (key) {
            case "cats", "trees":
                if (notValidCatsAndTrees(entry, criteria, key)) {
                    return false;
                }
                break;
            case "pomeranians", "goldfish":
                if (notValidPomeraniansAndGoldFish(entry, criteria, key)) {
                    return false;
                }
                break;
            default:
                if (notValidDefault(entry, criteria, key)) {
                    return false;
                }
        }
        return true;
    }

    private boolean notValidDefault(Map.Entry<String, Optional<Integer>> entry, Map<String, Optional<Integer>> criteria, String key) {
        return !entry.getValue().equals(criteria.get(key));
    }

    private boolean notValidPomeraniansAndGoldFish(Map.Entry<String, Optional<Integer>> entry, Map<String, Optional<Integer>> criteria, String key) {
        return entry.getValue().get() >= criteria.get(key).get();
    }

    private boolean notValidCatsAndTrees(Map.Entry<String, Optional<Integer>> entry, Map<String, Optional<Integer>> criteria, String key) {
        return entry.getValue().get() <= criteria.get(key).get();
    }


    private void createNullProperties() {
        properties.put("children", Optional.empty());
        properties.put("cats", Optional.empty());
        properties.put("samoyeds", Optional.empty());
        properties.put("pomeranians", Optional.empty());
        properties.put("akitas", Optional.empty());
        properties.put("vizslas", Optional.empty());
        properties.put("goldfish", Optional.empty());
        properties.put("trees", Optional.empty());
        properties.put("cars", Optional.empty());
        properties.put("perfumes", Optional.empty());
    }

    public int getId() {
        return id;
    }

    public Map<String, Optional<Integer>> getProperties() {
        return properties;
    }

    @Override
    public String toString() {
        return "Sue{" +
                "id=" + id +
                ", properties=" + properties +
                '}';
    }


}