package TZJanosi.y2015.day02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Wrapper {
    private List<Box> boxes = new ArrayList<>();

    public Wrapper(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            processLine(line);
        }
    }

    public long calculateRibbonLength() {
        return boxes.stream().mapToInt(b -> b.calculateRibbonLengthForWrapper()).sum();
    }

    public long calculateWrapperArea() {
        return boxes.stream().mapToInt(b -> b.calculateSurfaceForWrapper()).sum();
    }

    private void processLine(String line) {
        String[] inputNumbersAsStrings = line.split("x");
        List<Integer> orderedInputNumbers = Arrays.stream(inputNumbersAsStrings)
                .map(s -> Integer.valueOf(s))
                .sorted()
                .toList();
        Box box = new Box(orderedInputNumbers.get(0), orderedInputNumbers.get(1), orderedInputNumbers.get(2));
        boxes.add(box);
    }

    public List<Box> getBoxes() {
        return boxes;
    }
}
