package tzjanosi.y2017.day22.part1;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Map {
    private Set<Coordinate> infected = new HashSet<>();
    private Virus virus;

    public Map(List<String> input) {
        processInput(input);
    }

    public int steps(int count) {
        int counter = 0;
        while (counter < count) {
            virus.burst(infected);
            counter++;
        }
        return virus.getInfectionCount();
    }

    private void processInput(List<String> input) {
        buildVirus(input);
        for (int i = 0; i < input.size(); i++) {
            processLine(i, input.get(i));
        }
    }

    private void processLine(int y, String line) {
        char[] lineAsCharArray = line.toCharArray();
        for (int i = 0; i < lineAsCharArray.length; i++) {
            if (lineAsCharArray[i] == '#') {
                infected.add(new Coordinate(i, y));
            }
        }

    }

    private void buildVirus(List<String> input) {
        Coordinate direction = new Coordinate(0, -1);
        Coordinate position = new Coordinate(input.get(0).length() / 2, input.size() / 2);
        virus = new Virus(position, direction);
    }
}
