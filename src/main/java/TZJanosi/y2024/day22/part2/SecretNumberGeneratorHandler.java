package TZJanosi.y2024.day22.part2;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SecretNumberGeneratorHandler {
    private int numberOfIterations;
    private SecretNumberGenerator secretNumberGenerator;
    private Map<Integer, SecretNumberGenerator> generatorsWithPrices = new HashMap<>();
    private Map<List<Integer>, Integer> patternAndGain = new HashMap<>();

    public SecretNumberGeneratorHandler(int numberOfIterations) {
        this.numberOfIterations = numberOfIterations;
    }

    public long calculateSumOfFinalValues(List<Integer> initValues) {
        long output = 0;
        for (int i = 0; i < initValues.size(); i++) {
            Integer initValue = initValues.get(i);
            secretNumberGenerator = new SecretNumberGenerator(0, numberOfIterations);
            secretNumberGenerator.setValue(initValue);
            output += secretNumberGenerator.createFinalValue();
            generatorsWithPrices.put(initValue, secretNumberGenerator);
        }
        return output;
    }

    public int calculateGains(int maxLengthOfChangePattern) {
        for (SecretNumberGenerator generator : generatorsWithPrices.values()) {
            Map<List<Integer>, Integer> patternsAndPrices = generator.createAllPricesToChangePatterns(maxLengthOfChangePattern);
            combine(patternsAndPrices);
        }
        Map.Entry<List<Integer>, Integer> maxEntry = getMaxFromPatternAndGain();
//        System.out.println(maxEntry);
        return maxEntry.getValue();
    }

    private Map.Entry<List<Integer>, Integer> getMaxFromPatternAndGain() {
        return patternAndGain.entrySet()
                .stream()
                .max(Comparator.comparingInt(e -> e.getValue()))
                .orElseThrow(() -> new IllegalStateException("No values are present in the map!"));
    }

    private void combine(Map<List<Integer>, Integer> patternsAndPrices) {
        for (Map.Entry<List<Integer>, Integer> patternAndPrice : patternsAndPrices.entrySet()) {
            List<Integer> key = patternAndPrice.getKey();
            if (!patternAndGain.containsKey(key)) {
                patternAndGain.put(key, patternAndPrice.getValue());
            } else {
                Integer actual = patternAndGain.get(key);
                Integer newValue = actual + patternAndPrice.getValue();
                patternAndGain.put(key, newValue);
            }
        }
    }

    public Map<Integer, SecretNumberGenerator> getGeneratorsWithPrices() {
        return generatorsWithPrices;
    }
}
