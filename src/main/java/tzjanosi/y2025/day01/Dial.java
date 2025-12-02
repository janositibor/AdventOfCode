package tzjanosi.y2025.day01;

import java.util.ArrayList;
import java.util.List;

public class Dial {
    private List<Integer> steps = new ArrayList<>();
    private int startingPosition = 50;
    private int maxDecrement;

    public Dial(List<String> input) {
        processInput(input);
    }

    public int calculatePassword() {
        int output = 0;
        int position = startingPosition;
        for (int i = 0; i < steps.size(); i++) {
            position += steps.get(i);
            if (position % 100 == 0) {
                output++;
            }
        }
        return output;
    }

    public int calculatePasswordPart2() {
        int output = 0;
        int position = startingPosition;
        for (int i = 0; i < steps.size(); i++) {
            int step = steps.get(i);
            for (int j = 0; j < Math.abs(step); j++) {
                if (step < 0) {
                    position--;
                } else {
                    position++;
                }
                if (position % 100 == 0) {
                    output++;
                }
            }
        }
        return output;
    }

    public int calculatePasswordPart2Ver2() {
        int output = 0;
        int position = startingPosition + (Math.max(maxDecrement / 100, 1) * steps.size() * 100);
        for (int i = 0; i < steps.size(); i++) {
            int nextPosition = position + steps.get(i);
            output += calculateCrossing(position, nextPosition);
            position = nextPosition;
        }
        return output;
    }

    private int calculateCrossing(int position, int nextPosition) {
        int difference = (position / 100) - (nextPosition / 100);
        int output = Math.abs(difference);
        if (nextPosition < position) {
            if (nextPosition % 100 == 0) {
                output++;
            }
            if (position % 100 == 0) {
                output--;
            }
        }
        return output;
    }

    private void processInput(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            Integer step = createStepFromLine(input.get(i));
            steps.add(step);
        }
    }

    private int createStepFromLine(String line) {
        Integer length = Integer.valueOf(line.substring(1));
        if (line.charAt(0) == 'L') {
            maxDecrement = Math.max(maxDecrement, length);
        }
        return line.charAt(0) == 'R' ? length : -1 * length;
    }
}
