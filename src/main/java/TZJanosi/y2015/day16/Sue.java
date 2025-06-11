package TZJanosi.y2015.day16;

import java.util.HashMap;
import java.util.Map;

public class Sue {
    private int id;
    private Map<String, Integer> properties = new HashMap<>();

    public Sue(int id, Map<String, Integer> knownProperties) {
        this.id = id;
        createNullProperties();
        properties.putAll(knownProperties);
    }

    public boolean fulfill(Map<String, Integer> criteria) {
        for (Map.Entry<String, Integer> entry : properties.entrySet()) {
            if (entry.getValue() != null) {
                String key = entry.getKey();
                if (entry.getValue() != criteria.get(key)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void createNullProperties() {
        properties.put("children", null);
        properties.put("cats", null);
        properties.put("samoyeds", null);
        properties.put("pomeranians", null);
        properties.put("akitas", null);
        properties.put("vizslas", null);
        properties.put("goldfish", null);
        properties.put("trees", null);
        properties.put("cars", null);
        properties.put("perfumes", null);
    }

    public int getId() {
        return id;
    }

    public Map<String, Integer> getProperties() {
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

