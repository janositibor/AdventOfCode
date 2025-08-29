package tzjanosi.y2017.day02;

import java.util.List;

public class CheckSum {
    private List<String> input;

    public CheckSum(List<String> input) {
        this.input = input;
    }

    public int calculateCheckSum() {
        int output = 0;
        for (String line : input) {
            output += calculateDifference(line);
        }
        return output;
    }

    public int calculateCheckSumPart2() {
        int output = 0;
        for (String line : input) {
            output += calculateEvenlyDivides(line);
        }
        return output;
    }

    private int calculateEvenlyDivides(String line) {
        String[] words = line.split("[ ,\t]");

        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                int value1 = Integer.parseInt(words[i]);
                int value2 = Integer.parseInt(words[j]);
                int numerator = Math.max(value1, value2);
                int denominator = Math.min(value1, value2);
                if (numerator % denominator == 0) {
                    return numerator / denominator;
                }
            }
        }
        return 0;
    }

    private int calculateDifference(String line) {
        String[] words = line.split("[ ,\t]");
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < words.length; i++) {
            int value = Integer.parseInt(words[i]);
            min = Math.min(min, value);
            max = Math.max(value, max);
        }
        return max - min;
    }
}
