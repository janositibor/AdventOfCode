package tzjanosi.y2015.day05;

import java.util.ArrayList;
import java.util.List;

public class Verifier {
    private List<Word> words = new ArrayList<>();

    public Verifier(List<String> input) {
        for (String inputWord : input) {
            words.add(new Word(inputWord));
        }
    }

    public int countNiceStrings() {
        return (int) words.stream().filter(Word::isNice).count();
    }

    public int countNiceStringsPart2() {
        return (int) words.stream().filter(Word::isNicePart2).count();
    }

}
