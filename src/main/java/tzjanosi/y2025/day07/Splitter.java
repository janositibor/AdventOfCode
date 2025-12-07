package tzjanosi.y2025.day07;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Splitter {
    private List<List<Integer>> hardware = new ArrayList<>();
    private Set<Integer> beams = new HashSet<>();

    public Splitter(List<String> input) {
        processInput(input);
    }

    public int split() {
        int output = 0;
        for (int i = 0; i < hardware.size(); i++) {
            List<Integer> splitterLine = hardware.get(i);
            output += splitLine(splitterLine);
        }
        return output;
    }

    private int splitLine(List<Integer> splitterLine) {
        int output = 0;
        int numberOfIncomingBeams = beams.size();
        for (int i = 0; (i < splitterLine.size() && output < numberOfIncomingBeams); i++) {
            int position = splitterLine.get(i);
            if (beams.contains(position)) {
                output++;
                updateBeams(position);
            }
        }
        return output;
    }

    private void updateBeams(int position) {
        beams.remove(position);
        beams.add(position - 1);
        beams.add(position + 1);
    }

    private void processInput(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            if (i == 0) {
                initBeams(line);
            } else {
                if (line.contains("^")) {
                    lineProcessor(line);
                }
            }
        }
    }

    private void lineProcessor(String line) {
        List<Integer> splitterLine = new ArrayList<>();
        int from = -1;
        do {
            from = line.indexOf('^', from + 1);
            if (from > -1) {
                splitterLine.add(from);
            }
        }
        while (from > -1);
        hardware.add(splitterLine);
    }

    private void initBeams(String line) {
        beams.add(line.indexOf('S'));
    }
}
