package tzjanosi_temp.y2015.day10;

import java.util.ArrayList;
import java.util.List;

public class DeCoder {
    private List<Pair> codeList = new ArrayList<>();

    public int lengthOfCodeAfterRepetition(int repetition, String input) {
        return repeatedCoder(repetition, input).length();
    }

    public String repeatedCoder(int repetition, String input) {
        String actual = input;
        for (int i = 0; i < repetition; i++) {
            String next = coder(actual);
            actual = next;
        }
        return actual;
    }

    public String coder(String input) {
        buildCodeList(input);
        return buildCodeFromCodeList();
    }


    private String buildCodeFromCodeList() {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < codeList.size(); i++) {
            Pair tempPair = codeList.get(i);
            String stringToAdd = tempPair.getCount() + tempPair.getLetter();
            output.append(stringToAdd);
        }
        return output.toString();
    }

    private void buildCodeList(String input) {
        codeList = new ArrayList<>();
        int count = 0;
        char actual = 0;

        for (int i = 0; i < input.length(); i++) {
            if (actual == input.charAt(i)) {
                count++;
            } else {
                if (count > 0) {
                    Pair pair = new Pair(count, String.valueOf(actual));
                    codeList.add(pair);
                }
                actual = input.charAt(i);
                count = 1;
            }
        }
        Pair pair = new Pair(count, String.valueOf(actual));
        codeList.add(pair);
    }

    public List<Pair> getCodeList() {
        return codeList;
    }
}
