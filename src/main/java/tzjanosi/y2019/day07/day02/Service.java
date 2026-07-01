package tzjanosi.y2019.day07.day02;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Service {
    private String input;
    private long maxThruster = Long.MIN_VALUE;
    private List<Amplifier> amplifiers = new ArrayList<>();

    public Service(String input) {
        this.input = input;

    }

    public void findMaxLocalThruster(List<Integer> phases) {
        amplifiers.clear();
        for (int i = 0; i < phases.size(); i++) {
            Amplifier amplifier = new Amplifier(phases.get(i), input);
            amplifiers.add(amplifier);
        }
        long nextValue = 0;
        boolean stopped = false;
        int amplifierIndex = 0;
        while (!stopped) {
            Amplifier amplifier = amplifiers.get(amplifierIndex);
            Optional<Long> optionalNextValue = amplifier.run(nextValue);
            if (optionalNextValue.isEmpty()) {
                stopped = true;
            } else {
                nextValue = optionalNextValue.get();
                if (amplifierIndex == amplifiers.size() - 1) {
                    maxThruster = Math.max(maxThruster, nextValue);
                    amplifierIndex = 0;
                } else {
                    amplifierIndex++;
                }
            }


        }
    }


    public long findGlobalMaxThruster() {
        List<Integer> initialPhases = IntStream.rangeClosed(5, 9).mapToObj(x -> x).collect(Collectors.toList());
        List<List<Integer>> permutations = new Permutations<Integer>(initialPhases).permutations();
        for (int i = 0; i < permutations.size(); i++) {
            findMaxLocalThruster(permutations.get(i));
        }
        return maxThruster;
    }


}
