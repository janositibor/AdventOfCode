package tzjanosi.y2019.day04;

public class Service {
    private int limitMin = 382345;
    private int limitMax = 843167;

    public int counterPart2() {
        return counter(this::isValidPart2);
    }

    public int counterPart1() {
        return counter(this::isValidPart1);
    }

    @SuppressWarnings({"PMD.CognitiveComplexity", "PMD.CyclomaticComplexity"})
    private int counter(IntVarArgsPredicate condition) {
        int output = 0;
        int value = 0;
        for (int i = 3; i < 9; i++) {
            for (int j = i; j < 10; j++) {
                for (int k = j; k < 10; k++) {
                    for (int l = k; l < 10; l++) {
                        for (int m = l; m < 10; m++) {
                            for (int n = m; n < 10; n++) {
                                value = calculateValue(i, j, k, l, m, n);
                                if (limitMax < value) {
                                    return output;
                                }
                                if (limitMin <= value && condition.test(i, j, k, l, m, n)) {
                                    output++;
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new IllegalStateException("Unexpected value: " + value);
    }

    private boolean isValidPart2(int... args) {
        for (int i = 0; i < args.length - 1; i++) {
            if ((i == 0 || args[i - 1] != args[i]) && (args[i] == args[i + 1]) && (i == args.length - 2 || args[i + 1] != args[i + 2])) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidPart1(int... args) {
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i] == args[i + 1]) {
                return true;
            }
        }
        return false;
    }

    private int calculateValue(int... args) {
        int output = 0;
        for (int i = 0; i < args.length; i++) {
            output = 10 * output + args[i];
        }
        return output;
    }
}
