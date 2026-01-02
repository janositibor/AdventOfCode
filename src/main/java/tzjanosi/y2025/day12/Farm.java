package tzjanosi.y2025.day12;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Farm {
    private Set<Present> presents = new HashSet<>();
    private List<Region> regions = new ArrayList<>();

    public Farm(List<String> input) {
        processInput(input);
    }

    public int zeroCheck() {
        int output = 0;
        for (int i = 0; i < regions.size(); i++) {
            Region region = regions.get(i);
            if (region.zeroVersionCheck() > 0) {
                output++;
            }
        }
        return output;
    }

    private void processInput(List<String> input) {
        processPresents(input);
        processRegions(input);
    }

    private void processRegions(List<String> input) {
        for (int i = 30; i < input.size(); i++) {
            Region region = processLine(input.get(i));
            region.setPresents(presents);
            regions.add(region);
        }
    }

    private Region processLine(String line) {
        String[] parts = line.split(": ");
        String[] dimensions = parts[0].split("x");
        Region output = new Region(Integer.parseInt(dimensions[0]), Integer.parseInt(dimensions[1]));
        String[] numberOfPresents = parts[1].split(" ");
        for (int i = 0; i < numberOfPresents.length; i++) {
            int numberOfPresent = Integer.parseInt(numberOfPresents[i]);
            output.addPresentCount(numberOfPresent);
        }
        return output;
    }

    private void processPresents(List<String> input) {
        for (int i = 0; i < 30; i += 5) {
            String name = input.get(i).substring(0, 1);
            int size = getPresentSize(input.get(i + 1) + input.get(i + 2) + input.get(i + 3));
            Present present = new Present(Integer.parseInt(name), size);
            presents.add(present);
        }
    }

    private int getPresentSize(String s) {
        int output = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '#') {
                output++;
            }
        }
        return output;
    }
}
