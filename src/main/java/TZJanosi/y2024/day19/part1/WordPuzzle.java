package TZJanosi.y2024.day19.part1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordPuzzle {
    private String word;
    private List<String> bricks;
    private boolean isPossible = false;
    private List<String> goodParts;
    private static Set<String> nok = new HashSet<>();

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
                goodParts = parts;
                return;
            }
            for (int i = 0; (i < bricks.size() && !isPossible); i++) {
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
        if (word.length() > length && brick.equals(word.substring(0, length))) {
            return true;
        }
        if (word.length() == length && brick.equals(word)) {
            return true;
        }
        return false;
    }

    public List<String> getGoodParts() {
        return goodParts;
    }
}
