package tzjanosi.y2017.day14;

import java.util.HashSet;
import java.util.Set;

public class Grid {
    private String key;
    private int size = 128;
    private KnotHash knotHash;
    private Set<Set<Coordinate>> regions = new HashSet<>();
    private Set<Coordinate> ones = new HashSet<>();

    public Grid(String key) {
        this.key = key + "-";
    }

    public int countRegions() {
        buildRegions();
        processRegions();
        return regions.size();
    }

    private void processRegions() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Coordinate actual = new Coordinate(j, i);
                Set<Coordinate> neighbours = neighboursOf(actual);
                processNeighbours(actual, neighbours);
            }
        }
    }

    private void processNeighbours(Coordinate actual, Set<Coordinate> neighbours) {
        if (ones.contains(actual)) {
            for (Coordinate neighbour : neighbours) {
                if (ones.contains(neighbour)) {
                    processNeighbour(actual, neighbour);
                }
            }
        }
    }

    private void processNeighbour(Coordinate actual, Coordinate neighbour) {
        Set<Coordinate> actualRegion = findRegionFor(actual);
        Set<Coordinate> neighbourRegion = findRegionFor(neighbour);
        if (!actualRegion.equals(neighbourRegion)) {
            mergeRegions(actualRegion, neighbourRegion);
        }
    }

    private void mergeRegions(Set<Coordinate> actualRegion, Set<Coordinate> neighbourRegion) {
        Set<Coordinate> merged = new HashSet<>(actualRegion);
        merged.addAll(neighbourRegion);
        regions.remove(actualRegion);
        regions.remove(neighbourRegion);
        regions.add(merged);
    }

    private Set<Coordinate> findRegionFor(Coordinate coordinateToFind) {
        return regions.stream()
                .filter(s -> s.contains(coordinateToFind))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("Coordinate %s, not found!", coordinateToFind.toString())));
    }

    private Set<Coordinate> neighboursOf(Coordinate actual) {
        Set<Coordinate> output = new HashSet<>();
        if (actual.getX() < size - 1) {
            output.add(new Coordinate(actual.getX() + 1, actual.getY()));
        }
        if (actual.getY() < size - 1) {
            output.add(new Coordinate(actual.getX(), actual.getY() + 1));
        }
        return output;
    }

    private void buildRegions() {
        for (int i = 0; i < size; i++) {
            String password = key + i;
            knotHash = new KnotHash(password);
            String binString = knotHash.createHash();
            processLine(binString, i);
        }
    }

    private void processLine(String line, int y) {
        char[] charArray = line.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == '1') {
                Coordinate coordinate = new Coordinate(i, y);
                ones.add(coordinate);
                Set<Coordinate> set = new HashSet<>();
                set.add(coordinate);
                regions.add(set);
            }
        }
    }

    public int countUsed() {
        int output = 0;
        for (int i = 0; i < size; i++) {
            String password = key + i;
            knotHash = new KnotHash(password);
            String binString = knotHash.createHash();
            output += countStringInString(binString, '1');
        }
        return output;
    }

    private int countStringInString(String stringToCheck, char charToCount) {
        int result = 0;
        char[] charArray = stringToCheck.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == charToCount) {
                result++;
            }
        }
        return result;
    }
}
