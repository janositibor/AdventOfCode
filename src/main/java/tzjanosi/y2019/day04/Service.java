package tzjanosi.y2019.day04;

public class Service {
    private int limitMin = 382345;
    private int limitMax = 843167;

    @SuppressWarnings({"PMD.CognitiveComplexity", "PMD.CyclomaticComplexity"})
    public int counter() {
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
                                if (limitMin <= value && (i == j || j == k || k == l || l == m || m == n)) {
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

    private int calculateValue(int i, int j, int k, int l, int m, int n) {
        int output = 0;
        output = 10 * output + i;
        output = 10 * output + j;
        output = 10 * output + k;
        output = 10 * output + l;
        output = 10 * output + m;
        output = 10 * output + n;
        return output;
    }
}
