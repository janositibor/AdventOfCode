package TZJanosi.y2015.day05;

import java.util.List;

public class Word {
    private String value;

    public Word(String value) {
        this.value = value;
    }

    public boolean isNice() {
        return hasThreeVowels() && hasDouble() && !containsForbiddenStrings();
    }

    private boolean containsForbiddenStrings() {
        List<String> forbiddenStrings = List.of("ab", "cd", "pq", "xy");
        return forbiddenStrings
                .stream()
                .anyMatch(s -> value.contains(s));
    }

    private boolean hasDouble() {
        int index = 0;
        String[] valueAsString = value.split("");
        while (index < valueAsString.length - 1) {
            if (valueAsString[index].equals(valueAsString[index + 1])) {
                return true;
            }
            index++;
        }
        return false;
    }

    private boolean hasThreeVowels() {
        int vowelCount = 0;
        int index = 0;
        String[] valueAsString = value.split("");
        while (vowelCount < 3 && index < valueAsString.length) {
            if (isVowel(valueAsString[index])) {
                vowelCount++;
            }
            index++;
        }
        return vowelCount >= 3;
    }

    private boolean isVowel(String input) {
        List<String> vowels = List.of("a", "e", "i", "o", "u");
        return vowels.stream().anyMatch(s -> s.equals(input));
    }
}
