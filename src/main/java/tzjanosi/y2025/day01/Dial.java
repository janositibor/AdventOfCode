package tzjanosi.y2025.day01;

import java.util.ArrayList;
import java.util.List;

public class Dial {
    private List<Integer> steps = new ArrayList<>();
    private int startingPosition = 50;

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

    private void processInput(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            Integer step = createStepFromLine(input.get(i));
            steps.add(step);
        }
    }

    private int createStepFromLine(String line) {
        Integer length = Integer.valueOf(line.substring(1));
        return line.charAt(0) == 'R' ? length : -1 * length;
    }
}
