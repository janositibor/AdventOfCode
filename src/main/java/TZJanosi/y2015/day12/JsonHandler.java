package TZJanosi.y2015.day12;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class JsonHandler {
    private String input;
    private static final Pattern INT_PATTERN = Pattern.compile("^-?\\d+$");

    public JsonHandler(String input) {
        this.input = input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public int getSumOfNumbers() {
        List<Integer> numbers = getNumbers();
        return numbers.stream().mapToInt(x -> x.intValue()).sum();
    }

    public List<Integer> getNumbers() {
        String[] words = input.split("[\\[\\],\"{}:]");
        return Arrays.stream(words)
                .filter(w -> isIntegerRegex(w))
                .map(w -> Integer.valueOf(w))
                .toList();
    }

    public boolean isIntegerRegex(String s) {
        return s != null && INT_PATTERN.matcher(s.trim()).matches();
    }
}
