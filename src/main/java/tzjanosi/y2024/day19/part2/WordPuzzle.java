package tzjanosi.y2024.day19.part2;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class WordPuzzle {
    private String word;
    private List<String> bricks;
    private boolean isPossible;
    private static Set<String> nok = new HashSet<>();
    private static Map<String, Long> counts = new ConcurrentHashMap<>();
    private long numberOfGoodCombinations;

    public WordPuzzle(String word, List<String> bricks) {
        this.word = word;
        this.bricks = bricks;
    }

    public boolean processData() {
        buildWord(word, new ArrayList<>());
        return isPossible;
    }

    public void buildWord(String inputWord, List<String> parts) {
        if (!nok.contains(inputWord)) {
            if (inputWord.length() == 0) {
                isPossible = true;
                numberOfGoodCombinations++;
                return;
            }
            for (int i = 0; (i < bricks.size()); i++) {
                String brick = bricks.get(i);
                int length = brick.length();
                if (fit(brick, inputWord)) {
                    List<String> moreParts = new ArrayList<>(parts);
                    moreParts.add(brick);
                    buildWord(inputWord.substring(length), moreParts);
                }
            }
            if (!isPossible) {
                nok.add(inputWord);
            }
        }
    }

    private boolean fit(String brick, String word) {
        int length = brick.length();
        return word.length() > length && brick.equals(word.substring(0, length)) || (word.length() == length && brick.equals(word));
    }

    public long getNumberOfGoodCombinations() {
        return numberOfGoodCombinations;
    }

    public long processData2() {
        numberOfGoodCombinations = countPossibilities(word);
        return numberOfGoodCombinations;
    }

    private long countPossibilities(String word) {
        if (word.length() == 0) {
            return 1;
        }
        long output = 0;
        if (counts.containsKey(word)) {
            return counts.get(word);
        } else {
            for (int i = 0; (i < bricks.size()); i++) {
                String brick = bricks.get(i);
                int length = brick.length();
                if (fit(brick, word)) {
                    output += countPossibilities(word.substring(length));
                }
            }
        }
        counts.put(word, output);
        return output;
    }

    public static void reset() {
        nok = new HashSet<>();
        counts = new HashMap<>();
    }
}
