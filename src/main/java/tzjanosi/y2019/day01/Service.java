package tzjanosi.y2019.day01;

import java.util.ArrayList;
import java.util.List;

public class Service {
    private List<Integer> masses = new ArrayList<>();

    public Service(List<String> input) {
        processInput(input);
    }

    public int calculateFuelRequirements() {
        return masses.stream().mapToInt(x -> (x / 3) - 2).sum();
    }

    private void processInput(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            processLine(input.get(i));
        }
    }

    private void processLine(String line) {
        masses.add(Integer.parseInt(line));
    }
}
