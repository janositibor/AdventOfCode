package tzjanosi.y2019.day08;

import java.util.ArrayList;
import java.util.List;

public class Layer {
    private String data;
    private int xLimit;
    private int yLimit;
    private List<String> rows = new ArrayList<>();

    public Layer(int xLimit, int yLimit) {
        this.xLimit = xLimit;
        this.yLimit = yLimit;
    }

    public void show() {
        for (int i = 0; i < rows.size(); i++) {
            String converted = rows.get(i).replaceAll("0", " ").replaceAll("1", "#");
            System.out.println(converted);
        }
    }

    public int numberOfZeros() {
        return countOfChar('0');
    }

    public int checkSum() {
        return countOfChar('1') * countOfChar('2');
    }

    private int countOfChar(char c) {
        int output = 0;
        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) == c) {
                output++;
            }
        }
        return output;
    }

    public void fillData(String input) {
        if (input.length() != getSize()) {
            throw new IllegalArgumentException("The length of input String is not valid: " + input);
        }
        processInput(input);
    }

    public int getSize() {
        return xLimit * yLimit;
    }

    private void processInput(String input) {
        data = input;
        for (int i = 0; i < yLimit; i++) {
            int from = i * xLimit;
            int to = (i + 1) * xLimit;
            String row = input.substring(from, to);
            rows.add(row);
        }
    }

    public int getxLimit() {
        return xLimit;
    }

    public int getyLimit() {
        return yLimit;
    }

    public List<String> getRows() {
        return rows;
    }
}
