package tzjanosi.y2018.day23;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Service {
    private Set<Nanobot> nanobots = new HashSet<>();

    public Service(List<String> input) {
        processInput(input);
    }

    public int calculateNanobotsNearByTheStrongest() {
        Nanobot strongest = findStrongest();

        return (int) nanobots.stream().filter(strongest::isWithinRadius).count();

    }

    private Nanobot findStrongest() {
        return nanobots.stream().sorted(Comparator.comparingLong(Nanobot::getRadius).reversed()).findFirst().orElseThrow(() -> new IllegalStateException("Empty nanobots set!"));
    }

    private void processInput(List<String> input) {
        for (String line : input) {
            processLine(line);
        }
    }

    private void processLine(String line) {
        String[] parts = line.split(">, r=");
        long radius = Long.parseLong(parts[1]);
        String[] coordinates = parts[0].substring(5).split(",");
        long x = Long.parseLong(coordinates[0]);
        long y = Long.parseLong(coordinates[1]);
        long z = Long.parseLong(coordinates[2]);

        nanobots.add(new Nanobot(new Coordinate(x, y, z), radius));

    }
}
