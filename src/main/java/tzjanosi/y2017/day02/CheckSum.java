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
