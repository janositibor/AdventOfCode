package tzjanosi.y2017.day14;

public class Grid {
    private String key;
    private int size = 128;
    private KnotHash knotHash;

    public Grid(String key) {
        this.key = key + "-";
    }

    public int countUsed() {
        int output = 0;
        for (int i = 0; i < size; i++) {
            String password = key + i;
            knotHash = new KnotHash(password);
            String binString = knotHash.createHash();
            output += countStringInString(binString, '1');
        }
        return output;
    }

    private int countStringInString(String stringToCheck, char charToCount) {
        int result = 0;
        char[] charArray = stringToCheck.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == charToCount) {
                result++;
            }
        }
        return result;
    }
}
