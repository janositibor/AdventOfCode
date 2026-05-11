package tzjanosi.y2018.day17;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Soil {
    private Set<Coordinate> walls = new HashSet<>();
    private Set<Coordinate> wet = new HashSet<>();
    private Set<Coordinate> inReservoirs = new HashSet<>();
    private Set<Coordinate> alreadyFilled = new HashSet<>();

    private Map<String, Integer> limits = new ConcurrentHashMap<>(Map.of("min", Integer.MAX_VALUE, "max", Integer.MIN_VALUE));

    public Soil(List<String> input) {
        processInput(input);
    }

    public int findWaterInReservoirs() {
        irrigate();
        return inReservoirs.size();
    }

    public int irrigate() {
        Set<Coordinate> entryPoints = new HashSet<>();
        entryPoints.add(new Coordinate(500, 0));
        while (!entryPoints.isEmpty()) {
            Coordinate entry = entryPoints.iterator().next();
            entryPoints.remove(entry);
            if (!wet.contains(entry)) {
                Coordinate pointToFill = falling(entry, false);
                if (pointToFill.getY() > 0 && !alreadyFilled.contains(pointToFill)) {
                    alreadyFilled.add(pointToFill);
                    Set<Coordinate> newSprings = fill(pointToFill);
                    entryPoints.addAll(newSprings);
                }
            }
        }
        return wet.size();
    }

    private Set<Coordinate> fill(Coordinate pointToFill) {
        boolean fromBottom = walls.contains(pointToFill);
        Set<Coordinate> output = new HashSet<>();

        boolean inProgress = true;
        int wallFrom;
        int wallTo;
        if (fromBottom) {
            wallFrom = plateauFrom(pointToFill);
            wallTo = plateauTo(pointToFill);
        } else {
            Coordinate temp = pointToFill.shift(new Coordinate(0, -1));
            wallFrom = findWallFrom(temp);
            wallTo = findWallTo(temp);
        }
        Coordinate actual = pointToFill.shift(new Coordinate(0, -1));
        while (inProgress) {
            int actualWallFrom = findWallFrom(actual);
            int actualWallTo = findWallTo(actual);
            boolean top = true;
            if (regularLine(wallFrom, actualWallFrom, actualWallTo, wallTo)) {
                top = false;
            }
            water(new Coordinate(Math.max(wallFrom, actualWallFrom), actual.getY()), new Coordinate(Math.min(wallTo, actualWallTo), actual.getY()), top);
            Set<Coordinate> waterfall = createWaterfall(new Coordinate(Math.max(wallFrom, actualWallFrom), actual.getY()), new Coordinate(Math.min(wallTo, actualWallTo), actual.getY()));
            output.addAll(waterfall);

            if (regularLine(wallFrom, actualWallFrom, actualWallTo, wallTo)) {
                actual = actual.shift(new Coordinate(0, -1));
            } else {
                inProgress = false;
                if (actualWallFrom < wallFrom) {
                    output.add(new Coordinate(wallFrom - 1, actual.getY()));
                }
                if (actualWallTo > wallTo) {
                    output.add(new Coordinate(wallTo + 1, actual.getY()));
                }
            }
        }
        return output;
    }

    private boolean regularLine(int wallFrom, int actualWallFrom, int actualWallTo, int wallTo) {
        return (wallFrom <= actualWallFrom) && (actualWallTo <= wallTo);
    }

    private Set<Coordinate> createWaterfall(Coordinate from, Coordinate to) {
        Set<Coordinate> output = new HashSet<>();
        int y = from.getY();
        for (int i = from.getX(); i <= to.getX(); i++) {
            Coordinate actual = new Coordinate(i, y);
            Coordinate below = actual.shift(new Coordinate(0, 1));
            if (!wet.contains(below) && !walls.contains(below)) {
                falling(actual, true);
            }
        }

        return output;
    }

    private int findWallTo(Coordinate actual) {
        return walls.stream()
                .filter(b -> (b.getY() == actual.getY() && b.getX() > actual.getX()))
                .min(Comparator.comparingInt(Coordinate::getX))
                .map(Coordinate::getX)
                .orElse(Integer.MAX_VALUE);
    }

    private int findWallFrom(Coordinate actual) {
        return walls.stream()
                .filter(b -> (b.getY() == actual.getY() && b.getX() < actual.getX()))
                .max(Comparator.comparingInt(Coordinate::getX))
                .map(Coordinate::getX)
                .orElse(Integer.MIN_VALUE);
    }

    private int plateauTo(Coordinate entry) {
        Coordinate actual = entry;
        while (walls.contains(actual)) {
            actual = actual.shift(new Coordinate(1, 0));
        }
        return actual.getX() - 1;
    }

    private int plateauFrom(Coordinate entry) {
        Coordinate actual = entry;
        while (walls.contains(actual)) {
            actual = actual.shift(new Coordinate(-1, 0));
        }
        return actual.getX() + 1;
    }

    private Coordinate falling(Coordinate from, boolean reservoir) {
        Coordinate to = walls.stream()
                .filter(b -> (b.getX() == from.getX() && b.getY() > from.getY()))
                .min(Comparator.comparingInt(Coordinate::getY))
                .orElse(new Coordinate(from.getX(), -1));
        water(from, (to.getY() == -1) ? new Coordinate(to.getX(), limits.get("max")) : to, !reservoir);
        return to;
    }

    private void water(Coordinate from, Coordinate to, boolean top) {
        boolean reservoir = !top;
        for (int x = from.getX(); x <= to.getX(); x++) {
            for (int y = from.getY(); y <= to.getY(); y++) {
                Coordinate actualPosition = new Coordinate(x, y);
                if (limits.get("min") <= actualPosition.getY() && actualPosition.getY() <= limits.get("max") && !walls.contains(actualPosition)) {
                    wet.add(actualPosition);
                    if (reservoir) {
                        inReservoirs.add(actualPosition);
                    }
                }
            }
        }
    }

    public void print() {
        int minX = findMinX() - 1;
        int maxX = findMaxX() + 1;
        System.out.println("x min: " + minX);
        System.out.println("x max: " + maxX);
        System.out.println("y min: " + limits.get("min"));
        System.out.println("y max: " + limits.get("max"));

        for (int i = limits.get("min"); i <= limits.get("max"); i++) {
            StringBuilder line = new StringBuilder();
            for (int j = minX; j <= maxX; j++) {
                Coordinate actual = new Coordinate(j, i);
                if (walls.contains(actual)) {
                    line.append('#');
                } else if (inReservoirs.contains(actual)) {
                    line.append('+');
                } else if (wet.contains(actual)) {
                    line.append('~');
                } else {
                    line.append('.');

                }
            }
            System.out.println(line);
        }
    }

    private int findMinX() {
        return walls.stream().mapToInt(Coordinate::getX).min().getAsInt();
    }

    private int findMaxX() {
        return walls.stream().mapToInt(Coordinate::getX).max().getAsInt();
    }

    private void processInput(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            processLine(input.get(i));
        }
    }

    private void processLine(String line) {
        String[] parts = line.split(", ");
        String[] parts2 = parts[1].substring(2).split("\\.\\.");

        int varMin = Integer.parseInt(parts2[0]);
        int varMax = Integer.parseInt(parts2[1]);
        int constant = Integer.parseInt(parts[0].substring(2));

        buildWall(parts[0].charAt(0), constant, varMin, varMax);
    }

    private void buildWall(char direction, int constant, int varMin, int varMax) {
        for (int i = varMin; i <= varMax; i++) {
            Coordinate brick;
            if (direction == 'x') {
                brick = new Coordinate(constant, i);
            } else {
                brick = new Coordinate(i, constant);
            }
            adjustLimits(brick);
            walls.add(brick);
        }
    }

    private void adjustLimits(Coordinate brick) {
        int actualY = brick.getY();
        if (actualY < limits.get("min")) {
            limits.replace("min", actualY);
        }
        if (actualY > limits.get("max")) {
            limits.replace("max", actualY);
        }
    }
}
