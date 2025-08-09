package tzjanosi.y2016.day16;

import java.math.BigInteger;

public class Disk {
    private int size;
    private String startingString;
    private String fullContent;

    public Disk(int size, String startingString) {
        this.size = size;
        this.startingString = startingString;
    }

    public String erase() {
        fill();
        return calculateCheckSum(fullContent);
    }

    private String calculateCheckSum(String input) {
        String content = input;
        while (content.length() % 2 == 0) {
            String nextCheckSum = checkSum(content);
            content = nextCheckSum;
        }
        return content;
    }

    private String checkSum(String content) {
        if (content.length() % 2 == 1) {
            throw new IllegalArgumentException(String.format("The content must be even for checkSum!\nThe content: %s\nIts length: %d", content, content.length()));
        }
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < content.length(); i += 2) {
            String nextDigit = (content.charAt(i) == content.charAt(i + 1) ? "1" : "0");
            output.append(nextDigit);
        }
        return output.toString();
    }

    private void fill() {
        String content = startingString;
        while (content.length() < size) {
            String extendedContent = extendString(content);
            content = extendedContent;
        }
        fullContent = content.substring(0, size);
    }

    private BigInteger extend(BigInteger input, int originalLength) {
        String binaryString = input.toString(2);
        int length = binaryString.length();
        BigInteger inverse = BigInteger.TWO.pow(length).subtract(BigInteger.ONE).subtract(input);
        BigInteger reversed = new BigInteger(new StringBuilder(convertValueToString(inverse.toString(2), originalLength)).reverse().toString(), 2);

        BigInteger temp = input;
        return temp.multiply(BigInteger.TWO.pow(length + 1)).add(reversed);
    }

    public String extendString(String input) {
        int expectedLength = 2 * input.length() + 1;
        String value = extend(new BigInteger(input, 2), input.length()).toString(2);
        return convertValueToString(value, expectedLength);
    }

    private static String convertValueToString(String value, int expectedLength) {
        return String.format("%" + expectedLength + "s", value).replace(' ', '0');
    }
}
