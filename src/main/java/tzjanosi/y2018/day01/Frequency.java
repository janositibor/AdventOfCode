package tzjanosi.y2018.day01;

import java.util.ArrayList;
import java.util.List;

public class Frequency {
    private List<Integer> shifts = new ArrayList<>();

    public Frequency(List<String> input) {
        processLines(input);
    }

    public int sumOfShifts() {
        return shifts.stream()
                .mapToInt(d -> d)
                .sum();
    }

    private void processLines(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            processLine(input.get(i));
        }
    }

    private void processLine(String line) {
        shifts.add(Integer.parseInt(line));
    }
}
