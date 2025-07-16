package tzjanosi_temp.y2024.day21;

import java.util.Objects;

public class StringIntegerPair {
    private String letter;
    private int level;

    public StringIntegerPair(String letter, int level) {
        this.letter = letter;
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StringIntegerPair that = (StringIntegerPair) o;
        return level == that.level && Objects.equals(letter, that.letter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(letter, level);
    }
}
