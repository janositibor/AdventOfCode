package tzjanosi.y2016.day16;

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
            content = extendString(content);
        }
        fullContent = content.substring(0, size);
    }

    private String extend(String input) {
        String reversedAsString = inverseAndReverse(input);
        return input + "0" + reversedAsString;
    }

    private String inverseAndReverse(String input) {
        StringBuilder sb = new StringBuilder();
        for (int i = input.length() - 1; i >= 0; i--) {
            char next = input.charAt(i) == '0' ? '1' : '0';
            sb.append(next);
        }
        return sb.toString();
    }

    public String extendString(String input) {
        String value = extend(input);
        return convertValueToString(value, 2 * input.length() + 1);
    }

    private static String convertValueToString(String value, int expectedLength) {
        return String.format("%" + expectedLength + "s", value).replace(' ', '0');
    }
}
