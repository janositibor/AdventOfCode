package tzjanosi.y2018.day06;

import java.util.*;
import java.util.stream.Collectors;

public class ChronalMap {
    private List<LightHouse> lightHouses = new ArrayList<>();
    private Map<String, Integer> limits;
    private List<Position> positions = new ArrayList<>();
    private Set<LightHouse> infiniteLocations;

    public ChronalMap(List<String> input) {
        processInput(input);
        buildLimits();
        buildPositions();
        calculateDistancesForPositions();
        buildiInfiniteLocations();
    }

    private void buildiInfiniteLocations() {
        infiniteLocations = positions.stream().filter(p -> !p.inside(limits)).filter(p -> p.getNearestLightHouse().isPresent()).map(p -> p.getNearestLightHouse().get()).collect(Collectors.toSet());
    }

    public void draw() {
        int xMinLimit = limits.get("xMinLimit");
        int yMinLimit = limits.get("yMinLimit");
        int xMaxLimit = limits.get("xMaxLimit");
        int yMaxLimit = limits.get("yMaxLimit");

        for (int x = xMinLimit; x <= xMaxLimit; x++) {
            StringBuffer line = new StringBuffer();
            for (int y = yMinLimit; y <= yMaxLimit; y++) {
                line.append(getMark(x, y));
            }
            System.out.println(line);
        }
    }

    private char getMark(int x, int y) {
        Coordinate coordinate = new Coordinate(x, y);
        if (lightHouses.contains(coordinate)) {
            return getLocationMark(coordinate);
        }
        return Character.toLowerCase(getPositionMark(coordinate));
    }

    private char getPositionMark(Coordinate coordinate) {
        char result;
        Position position = new Position(coordinate);
        int index = positions.indexOf(position);
        if (positions.get(index).getNearestLightHouse().isPresent()) {
            result = positions.get(index).getNearestLightHouse().get().getName();
        } else {
            result = '-';
        }
        return result;
    }

    private char getLocationMark(Coordinate coordinate) {
        int index = lightHouses.indexOf(coordinate);
        return lightHouses.get(index).getName();
    }

    public int greatestArea() {
        Map<LightHouse, Long> tempMap = positions.stream().filter(position -> position.getNearestLightHouse().isPresent()).collect(Collectors.groupingBy(p -> p.getNearestLightHouse().get(), Collectors.counting()));
        return (int) tempMap.entrySet().stream().filter(entry -> !infiniteLocations.contains(entry.getKey())).mapToLong(Map.Entry<LightHouse, Long>::getValue).max().getAsLong();
    }

    private void calculateDistancesForPositions() {
        for (Position position : positions) {
            position.addLightHouses(lightHouses);
            position.findNearestLightHouse();
        }
    }

    private void buildPositions() {
        int xMinLimit = limits.get("xMinLimit");
        int yMinLimit = limits.get("yMinLimit");
        int xMaxLimit = limits.get("xMaxLimit");
        int yMaxLimit = limits.get("yMaxLimit");

        for (int x = xMinLimit; x <= xMaxLimit; x++) {
            for (int y = yMinLimit; y <= yMaxLimit; y++) {
                positions.add(new Position(new Coordinate(x, y)));
            }
        }
    }

    private void buildLimits() {
        limits = new HashMap<>();
        int xMinLimit = Integer.MAX_VALUE;
        int yMinLimit = Integer.MAX_VALUE;
        int xMaxLimit = Integer.MIN_VALUE;
        int yMaxLimit = Integer.MIN_VALUE;
        for (LightHouse lightHouse : lightHouses) {
            Coordinate coordinate = lightHouse.getCoordinate();
            xMinLimit = Math.min(xMinLimit, coordinate.getX());
            yMinLimit = Math.min(yMinLimit, coordinate.getY());
            xMaxLimit = Math.max(xMaxLimit, coordinate.getX());
            yMaxLimit = Math.max(yMaxLimit, coordinate.getY());
        }
        limits.put("xMinLimit", xMinLimit);
        limits.put("yMinLimit", yMinLimit);
        limits.put("xMaxLimit", xMaxLimit);
        limits.put("yMaxLimit", yMaxLimit);
    }

    private void processInput(List<String> input) {
        int starter = 65;
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            processLine(line, (char) (starter + i));
        }
    }

    private void processLine(String line, char name) {
        String[] parts = line.split(", ");
        lightHouses.add(new LightHouse(name, new Coordinate(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]))));
    }

    public List<LightHouse> getLightHouses() {
        return lightHouses;
    }
}
