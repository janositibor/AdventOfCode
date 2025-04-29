package TZJanosi.y2024.day19.part2;

import java.util.*;

public class Handler {
    private List<String> words = new ArrayList<>();
    private List<String> bricks;

    private List<String> fails = new ArrayList<>();
    private Map<String, Long> succed = new LinkedHashMap<>();

    public Handler(List<String> input) {
        processInput(input);
    }

    public int processData() {
        int output = 0;
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            WordPuzzle wordPuzzle = new WordPuzzle(word, bricks);
            if (wordPuzzle.processData()) {
                succed.put(word, wordPuzzle.getNumberOfGoodCombinations());
                output += wordPuzzle.getNumberOfGoodCombinations();
            } else {
                fails.add(word);
            }
//            System.out.printf("%d done!\n",i);
        }
        System.out.println("Succed: " + succed);
        System.out.println("Fails: " + fails);
        return output;
    }

    public long process2Data() {
        long output = 0;
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            WordPuzzle wordPuzzle = new WordPuzzle(word, bricks);
            if (wordPuzzle.processData2() > 0) {
                succed.put(word, wordPuzzle.getNumberOfGoodCombinations());
                output += wordPuzzle.getNumberOfGoodCombinations();
            } else {
                fails.add(word);
            }
//            System.out.printf("%d done!\n",i);
        }
        System.out.println("Succed: " + succed);
        System.out.println("Fails: " + fails);
        return output;
    }

    private void processInput(List<String> input) {
        processBricks(input.get(0));
        processWords(input);

    }

    private void processWords(List<String> input) {
        for (int i = 2; i < input.size(); i++) {
            words.add(input.get(i));
        }
    }

    private void processBricks(String input) {
        String[] bricksArray = input.split(", ");
        List<String> bricksTempList = Arrays.asList(bricksArray);
        bricksTempList.sort(Comparator.comparing(String::length).reversed());
        bricks = bricksTempList;
    }

    public List<String> getWords() {
        return words;
    }

    public List<String> getBricks() {
        return bricks;
    }


}
