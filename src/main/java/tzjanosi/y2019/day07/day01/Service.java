package tzjanosi.y2019.day07.day01;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Service {
    private String input;
    private long maxThruster = Long.MIN_VALUE;

    public Service(String input) {
        this.input = input;
    }

    private void iteration(List<Integer> inputPhases, long inputValue) {
        Iterator<Integer> iterator = inputPhases.iterator();
        for (int i = 0; i < inputPhases.size(); i++) {
            int inputPhase = iterator.next();
            long nextInputValue = getOutput(new long[]{inputPhase, inputValue});
            List<Integer> nextInputPhases = new ArrayList<>(inputPhases);
            nextInputPhases.remove((Integer) inputPhase);
            if (nextInputPhases.isEmpty()) {
                maxThruster = Math.max(maxThruster, nextInputValue);
            } else {
                iteration(nextInputPhases, nextInputValue);
            }
        }
    }

    public long findMaxThruster() {
        List<Integer> inputPhases = IntStream.rangeClosed(0, 4).mapToObj(x -> x).collect(Collectors.toList());
        iteration(inputPhases, 0);
        return maxThruster;
    }

    private long getOutput(long[] inputNumbers) {
        Amplifier amplifier = new Amplifier(inputNumbers, input);
        return amplifier.run();
    }
}
