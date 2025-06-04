package TZJanosi.y2015.day05;

import java.util.ArrayList;
import java.util.List;

public class Verifier {
    private List<Word> words = new ArrayList<>();
    private int countOfNiceStrings = 0;

    public Verifier(List<String> input) {
        for (String inputWord : input) {
            words.add(new Word(inputWord));
        }
    }

    public int countNiceStrings() {
        return (int) words.stream().filter(w -> w.isNice()).count();
    }

}
