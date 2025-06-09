package TZJanosi.y2015.day08;

import java.util.List;

public class CharacterCounter {
    private int countOfCharactersInCode;
    private int countOfCharactersInString;

    public CharacterCounter(List<String> input) {
        System.out.println("input.size: " + input.size());
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            processLine(line.substring(1, line.length() - 1));
        }
    }

    private void processLine(String line) {
        System.out.println(line + ": ");
        int countOfCharactersInTheLine = line.length();
        int countOfDoubleChars = 0;
        int countOfHexadecimalChars = 0;
        char[] charArray = line.toCharArray();
        countOfCharactersInCode += (charArray.length + 2);
        for (int i = 0; i < charArray.length - 1; i++) {
            if (charArray[i] == '\\') {
                if (charArray[i + 1] == '\\' || charArray[i + 1] == '"') {
                    countOfDoubleChars++;
                    i++;
                } else if (charArray[i + 1] == 'x') {
                    countOfHexadecimalChars++;
                    i += 3;
                }
            }
        }
        int output = countOfCharactersInTheLine - countOfDoubleChars - (3 * countOfHexadecimalChars);
        System.out.println(output);
        countOfCharactersInString += (output);
    }

    public int getCountOfCharacters() {
        return countOfCharactersInCode - countOfCharactersInString;
    }
}
