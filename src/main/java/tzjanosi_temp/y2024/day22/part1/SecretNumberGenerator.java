package tzjanosi_temp.y2024.day22.part1;

public class SecretNumberGenerator {
    private static final int FIRST_MULTIPLIER = 64;
    private static final int DIVIDER = 32;
    private static final int SECOND_MULTIPLIER = 2048;
    private static final long VALUE_FOR_MODULO_IN_PRUNE = 16777216L;
    private int numberOfIterations;
    private long value;

    public SecretNumberGenerator(long value, int numberOfIterations) {
        this.value = value;
        this.numberOfIterations = numberOfIterations;
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
    }

    public long createFinalValue() {
        for (int i = 0; i < numberOfIterations; i++) {
            createNextValue();
        }
        return value;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
