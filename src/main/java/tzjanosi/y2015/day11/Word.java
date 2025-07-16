package tzjanosi.y2015.day11;

public class Word {
    private static final int MIN_VALUE = 'a';
    private static final int MAX_VALUE = 'z';
    private static final int MAX_DIGIT = 7;
    private byte[] wordAsBytes;

    public String findNextValidWord(String input) {
        setWord(input);
        while (!isValid()) {
            increment();
        }
        return getWordAsString();
    }

    public String findNextValidWordPart2(String input) {
        setWord(input);
        do {
            increment();
        }
        while (!isValid());
        return getWordAsString();
    }

    public void setWord(String input) {
        wordAsBytes = input.getBytes();
    }

    public String getWordAsString() {
        return new String(wordAsBytes);
    }

    public void increment() {
        incrementDigit(MAX_DIGIT);
    }

    private void incrementDigit(int digit) {
        if (digit < 0 || digit > MAX_DIGIT) {
            throw new IllegalArgumentException(String.format("Invalid digit: %d", digit));
        }
        int actual = wordAsBytes[digit];
        if (actual < MAX_VALUE) {
            wordAsBytes[digit] = (byte) (actual + 1);
        } else {
            if (digit == 0) {
                throw new IllegalStateException(String.format("Maximal value of word is reached!"));
            }
            incrementDigit(digit - 1);
            wordAsBytes[digit] = MIN_VALUE;
        }
    }

    public boolean isValid() {
        return isInc() && !containsForbiddenChar() && containsSeparatedPairs();
    }

    public boolean containsSeparatedPairs() {
        boolean found = false;
        for (int i = 0; i < wordAsBytes.length - 1; i++) {
            if (wordAsBytes[i] == wordAsBytes[i + 1]) {
                if (found) {
                    return true;
                } else {
                    found = true;
                    i++;
                }
            }
        }
        return false;
    }

    public boolean containsForbiddenChar() {
        for (int i = 0; i < wordAsBytes.length - 2; i++) {
            if (wordAsBytes[i] == 'i' || wordAsBytes[i] == 'l' || wordAsBytes[i] == 'o') {
                return true;
            }
        }
        return false;
    }

    public boolean isInc() {
        for (int i = 0; i < wordAsBytes.length - 2; i++) {
            if (wordAsBytes[i + 2] - wordAsBytes[i + 1] == 1 && wordAsBytes[i + 1] - wordAsBytes[i] == 1) {
                return true;
            }
        }
        return false;
    }
}
