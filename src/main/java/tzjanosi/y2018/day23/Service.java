package tzjanosi.y2018.day23;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Service {
    private Set<Nanobot> nanobots = new HashSet<>();

    public Service(List<String> input) {
        processInput(input);
    }

    public long findManhattanDistanceToMostPopularPoint() {
        return findMostPopularPoint().manhattanDistanceTo(new Coordinate(0, 0, 0));
    }

    private Coordinate findMostPopularPoint() {
        Map<Long, Integer> limitsMap = createLimitsMap(nanobots, FourDCoordinate::getU);
        long u = findMaxOverlapCoordinate(limitsMap);
        Set<Nanobot> activeNanoBots = findActiveNanobots(nanobots, u, FourDCoordinate::getU);

        limitsMap = createLimitsMap(activeNanoBots, FourDCoordinate::getV);
        long v = findMaxOverlapCoordinate(limitsMap);
        activeNanoBots = findActiveNanobots(activeNanoBots, v, FourDCoordinate::getV);

        limitsMap = createLimitsMap(activeNanoBots, FourDCoordinate::getW);
        long w = findMaxOverlapCoordinate(limitsMap);
        activeNanoBots = findActiveNanobots(activeNanoBots, w, FourDCoordinate::getW);

        limitsMap = createLimitsMap(activeNanoBots, FourDCoordinate::getT);
        long t = findMaxOverlapCoordinate(limitsMap);

        FourDCoordinate fourDCoordinate = new FourDCoordinate(u, v, w, t);
        return fourDCoordinate.transformToCoordinate();
    }

    private Set<Nanobot> findActiveNanobots(Set<Nanobot> originalSetOfNanobots, long coordinateValue, Function<FourDCoordinate, Long> function) {
        return originalSetOfNanobots.stream()
                .filter(n -> ((function.apply(n.getFourDPosition()) - n.getRadius()) <= coordinateValue && coordinateValue <= (function.apply(n.getFourDPosition()) + n.getRadius())))
                .collect(Collectors.toSet());
    }

    private long findMaxOverlapCoordinate(Map<Long, Integer> limitsMap) {
        int mostOverlap = 0;
        long bestCoordinate = 0;
        int actualOverlap = 0;
        for (Map.Entry<Long, Integer> entry : limitsMap.entrySet()) {
            actualOverlap += entry.getValue();
            if (actualOverlap > mostOverlap) {
                mostOverlap = actualOverlap;
                bestCoordinate = entry.getKey();
            }
        }
        return bestCoordinate;
    }

    private Map<Long, Integer> createLimitsMap(Set<Nanobot> activeNanoBots, Function<FourDCoordinate, Long> function) {
        @SuppressWarnings("PMD.UseConcurrentHashMap")
        Map<Long, Integer> output = new TreeMap<>();
        Iterator<Nanobot> iterator = activeNanoBots.iterator();
        while (iterator.hasNext()) {
            Nanobot nanobot = iterator.next();
            long radius = nanobot.getRadius();
            FourDCoordinate fourDCoordinate = nanobot.getFourDPosition();
            long specificCoordinate = function.apply(fourDCoordinate);
            long from = specificCoordinate - radius;
            long to = specificCoordinate + radius + 1;
            if (!output.containsKey(from)) {
                output.put(from, 0);
            }
            output.put(from, output.get(from) + 1);
            if (!output.containsKey(to)) {
                output.put(to, 0);
            }
            output.put(to, output.get(to) - 1);
        }
        return output;
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
