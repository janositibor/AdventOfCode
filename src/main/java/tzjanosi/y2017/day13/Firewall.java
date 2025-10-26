package tzjanosi.y2017.day13;

import java.util.ArrayList;
import java.util.List;

public class Firewall {
    private List<Layer> layers = new ArrayList<>();

    public Firewall(List<String> input) {
        process(input);
    }

    private void process(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            processLine(input.get(i));
        }
    }

    private void processLine(String line) {
        String[] numbers = line.split(": ");
        int position = Integer.parseInt(numbers[0]);
        int depth = Integer.parseInt(numbers[1]);
        layers.add(new Layer(position, depth));
    }

    public int calculateTotalSeverity() {
        return layers.stream().mapToInt(Layer::severity).sum();
    }

    public boolean isCaught(int delay) {
        return layers.stream().anyMatch(l -> l.isCaught(delay));
    }

    public int calculateDelay() {
        boolean isCaught;
        int i = 0;
        do {
            isCaught = isCaught(i);
            i++;
        }
        while (isCaught);
        return --i;
    }
}
