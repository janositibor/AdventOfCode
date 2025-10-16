package tzjanosi.y2017.day10.part1;

import java.util.ArrayList;
import java.util.List;

public class KnotHash {
    private int startingPosition;
    private int[] girland = new int[256];
    private List<Integer> lengths = new ArrayList<>();

    public KnotHash(String input) {
        initGirland();
        processInput(input);
    }

    private void initGirland() {
        for (int i = 0; i < girland.length; i++) {
            girland[i] = i;
        }
    }

    private void processInput(String input) {
        String[] numbersAsString = input.split(",");
        for (int i = 0; i < numbersAsString.length; i++) {
            lengths.add(Integer.valueOf(numbersAsString[i]));
        }
    }

    public int knot() {
        initGirland();
        startingPosition = 0;
        for (int i = 0; i < lengths.size(); i++) {
            int length = lengths.get(i);
            mirror(length);
            startingPosition = getPosition(length + i);
        }
        return girland[0] * girland[1];
    }

    private int getPosition(int shift) {
        int position = startingPosition + shift;
        while (position >= girland.length) {
            position -= girland.length;
        }
        return position;
    }

    private void mirror(int length) {
        int steps = length / 2;
        for (int i = 0; i < steps; i++) {
            int position1Index = getPosition(i);
            int position2Index = getPosition(length - 1 - i);
            int temp = girland[position1Index];
            girland[position1Index] = girland[position2Index];
            girland[position2Index] = temp;
        }
    }
}
