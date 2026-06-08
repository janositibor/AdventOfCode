package tzjanosi.y2018.day22;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cave {
    private int depth;
    private Coordinate target;
    private Set<Region> regions = new HashSet<>();

    public Cave(List<String> input) {
        processInput(input);
        fillRegions();
    }

    public int calculateRisk() {
        return regions.stream().mapToInt(Region::getValue).sum();
    }

    private void fillRegions() {
        int xLimit = target.getX();
        int yLimit = target.getY();
        for (int i = 0; i <= xLimit; i++) {
            for (int j = 0; j <= yLimit; j++) {
                Coordinate coordinate = new Coordinate(i, j);
                Region region = new Region(coordinate);
                int erosion = calculateErosion(coordinate);
                region.setErosion(erosion);
                regions.add(region);
            }
        }
    }

    private void processInput(List<String> input) {
        depth = Integer.parseInt(input.get(0).split(" ")[1]);
        int x = Integer.parseInt(input.get(1).split(" ")[1].split(",")[0]);
        int y = Integer.parseInt(input.get(1).split(" ")[1].split(",")[1]);
        target = new Coordinate(x, y);
    }

    private int calculateErosion(Coordinate location) {
        long geologicIndex;
        if (target.equals(location)) {
            geologicIndex = 0;
        } else if (location.getY() == 0) {
            geologicIndex = (location.getX()) * 16807;
        } else if (location.getX() == 0) {
            geologicIndex = (location.getY()) * 48271;
        } else {
            Coordinate upCoordinate = new Coordinate(location.getX(), location.getY() - 1);
            Region upRegion = findRegion(upCoordinate);
            Coordinate leftCoordinate = new Coordinate(location.getX() - 1, location.getY());
            Region leftRegion = findRegion(leftCoordinate);
            geologicIndex = upRegion.getErosion() * leftRegion.getErosion();
        }
        return (int) ((geologicIndex + depth) % 20183);

    }

    private Region findRegion(Coordinate location) {
        return regions.stream()
                .filter(r -> r.getLocation().equals(location))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No region found at the location: " + location));
    }
}
