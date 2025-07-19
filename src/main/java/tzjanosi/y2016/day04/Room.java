package tzjanosi.y2016.day04;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Room {
    private String name;
    private String decryptedName;
    private int sectorID;
    private String checkSum;
    private Map<Character, Integer> numberOfLetters = new ConcurrentHashMap<>();

    public Room(String name, int sectorID, String checkSum) {
        this.name = name;
        this.sectorID = sectorID;
        this.checkSum = checkSum;
        buildNumberOfLetters();
    }

    private void buildNumberOfLetters() {
        for (int i = 0; i < name.length(); i++) {
            Character letter = name.charAt(i);
            if (!letter.equals('-')) {
                if (!numberOfLetters.containsKey(letter)) {
                    numberOfLetters.put(letter, 0);
                }
                numberOfLetters.put(letter, numberOfLetters.get(letter) + 1);
            }
        }
    }

    public void decryptName() {
        decryptedName = Arrays.stream(name.split(""))
                .map(this::decryptLetter)
                .collect(Collectors.joining());
    }

    private String decryptLetter(String letter) {
        if ("-".equals(letter)) {
            return " ";
        } else {
            int period = 'z' - 'a' + 1;
            int shift = sectorID % period;
            char letterToDecrypt = letter.charAt(0);
            int outputAsInt = letterToDecrypt + shift;
            if (letterToDecrypt + shift > 'z') {
                outputAsInt -= period;
            }
            return String.valueOf((char) outputAsInt);
        }
    }

    public boolean isValid() {
        String intCheckSum = convertCheckSumToIntString();
        String rightCheckSumString = buildRightCheckSumString();
        return intCheckSum.equals(rightCheckSumString);
    }

    private String buildRightCheckSumString() {
        return numberOfLetters.entrySet().stream()
                .map(Map.Entry::getValue)
                .sorted(Comparator.reverseOrder())
                .limit(5)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    private String convertCheckSumToIntString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < checkSum.length(); i++) {
            Character letter = checkSum.charAt(i);
            sb.append(numberOfLetters.get(letter));
        }
        return sb.toString();
    }

    public int getSectorID() {
        return sectorID;
    }

    public String getDecryptedName() {
        return decryptedName;
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", decryptedName='" + decryptedName + '\'' +
                ", sectorID=" + sectorID +
                ", checkSum='" + checkSum + '\'' +
                ", numberOfLetters=" + numberOfLetters +
                '}';
    }
}
