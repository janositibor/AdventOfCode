package tzjanosi.y2024.day22.part2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SecretNumberGenerator {
    private static final int FIRST_MULTIPLIER = 64;
    private static final int DIVIDER = 32;
    private static final int SECOND_MULTIPLIER = 2048;
    private static final long VALUE_FOR_MODULO_IN_PRUNE = 16777216L;
    private static final int NUMBER_OF_CHECKED_CHANGES = 4;
    private int numberOfIterations;
    private long value;
    private List<Integer> prices = new ArrayList<>();
    private List<Integer> changes = new ArrayList<>();
    private Map<List<Integer>, Integer> pricesToChangePatterns = new ConcurrentHashMap<>();

    public SecretNumberGenerator(long value, int numberOfIterations) {
        this.value = value;
        this.numberOfIterations = numberOfIterations;
        addPrice();
    }

    public long mix(long valueToConvert) {
        return valueToConvert ^ value;
    }

    public long prune(long valueToConvert) {
        return valueToConvert % VALUE_FOR_MODULO_IN_PRUNE;
    }

    private void mixAndPrune(long valueToConvert) {
        value = prune(mix(valueToConvert));
    }

    private void firstMultiplication() {
        long temp = value * FIRST_MULTIPLIER;
        mixAndPrune(temp);
    }

    private void division() {
        long temp = value / DIVIDER;
        mixAndPrune(temp);
    }

    private void secondMultiplication() {
        long temp = value * SECOND_MULTIPLIER;
        mixAndPrune(temp);
    }

    private void createNextValue() {
        firstMultiplication();
        division();
        secondMultiplication();
        addPrice();
    }

    private void addPrice() {
        int price = (int) value % 10;
        prices.add(price);
    }

    public long createFinalValue() {
        for (int i = 0; i < numberOfIterations; i++) {
            createNextValue();
        }
        createChangesFromPrices();
        return value;
    }

    private void createChangesFromPrices() {
        for (int i = 1; i < prices.size(); i++) {
            int difference = prices.get(i) - prices.get(i - 1);
            changes.add(difference);
        }
    }

    public Map<List<Integer>, Integer> createAllPricesToChangePatterns(int maxLengthOfChangePattern) {
        for (int i = 1; i < prices.size(); i++) {
            Integer price = prices.get(i);
            createPricesToChangePatterns(i, price, maxLengthOfChangePattern);
        }
        return pricesToChangePatterns;
    }

    public void createPricesToChangePatterns(int indexInPrice, Integer price, int maxLengthOfChangePattern) {
        int indexInChanges = indexInPrice - 1;
        if (indexInChanges >= changes.size()) {
            throw new IllegalArgumentException(String.format("Not enough changes! Size of changes is: %d, required indexInPrice is: %d !", changes.size(), indexInPrice));
        }
        int minIndex = Math.max(0, indexInChanges - maxLengthOfChangePattern);
        List<Integer> selectedChanges = new ArrayList<>(changes.subList(minIndex, indexInChanges + 1));
        int maxLengthOfPattern = selectedChanges.size();
        List<Integer> actualKey = selectedChanges;
        for (int i = 0; i < maxLengthOfPattern; i++) {
            if (actualKey.size() == NUMBER_OF_CHECKED_CHANGES && !pricesToChangePatterns.containsKey(actualKey)) {
                pricesToChangePatterns.put(actualKey, price);
            }
            actualKey = new ArrayList<>(actualKey);
            actualKey.remove(0);
        }
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public List<Integer> getPrices() {
        return prices;
    }

    public List<Integer> getChanges() {
        return changes;
    }
}
