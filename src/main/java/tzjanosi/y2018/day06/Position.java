package tzjanosi.y2018.day06;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Position {
    private Coordinate coordinate;
    private Map<LightHouse, Integer> distancesFromLightHouses = new ConcurrentHashMap<>();
    private Optional<LightHouse> nearestLightHouse = Optional.empty();
    private int totalDistanceFromLightHouses;

    public Position(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public boolean inside(Map<String, Integer> limits) {
        int xMinLimit = limits.get("xMinLimit");
        int yMinLimit = limits.get("yMinLimit");
        int xMaxLimit = limits.get("xMaxLimit");
        int yMaxLimit = limits.get("yMaxLimit");

        int x = coordinate.getX();
        int y = coordinate.getY();

        return x > xMinLimit && x < xMaxLimit && y > yMinLimit && y < yMaxLimit;
    }

    public void calculateTotalDistancesFromLightHouses() {
        totalDistanceFromLightHouses = distancesFromLightHouses.entrySet().stream().mapToInt(Map.Entry::getValue).sum();
    }

    public void findNearestLightHouse() {
        int min = Integer.MAX_VALUE;
        Optional<LightHouse> result = Optional.empty();
        for (Map.Entry<LightHouse, Integer> entry : distancesFromLightHouses.entrySet()) {
            int distance = entry.getValue();
            if (distance == min) {
                result = Optional.empty();
            }
            if (distance < min) {
                min = distance;
                result = Optional.of(entry.getKey());
            }
        }
        nearestLightHouse = result;
    }


    public void addLightHouses(List<LightHouse> lightHouses) {
        for (LightHouse lightHouse : lightHouses) {
            addLightHouse(lightHouse);
        }
    }

    private void addLightHouse(LightHouse lightHouse) {
        Coordinate lightHouseCoordinate = lightHouse.getCoordinate();
        distancesFromLightHouses.put(lightHouse, coordinate.getManhattanDistanceFrom(lightHouseCoordinate));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return Objects.equals(coordinate, position.coordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(coordinate);
    }

    @Override
    public String toString() {
        return "Position{" +
                "coordinate=" + coordinate +
                ", nearest=" + nearestLightHouse +
                '}';
    }


    public Optional<LightHouse> getNearestLightHouse() {
        return nearestLightHouse;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public int getTotalDistanceFromLightHouses() {
        return totalDistanceFromLightHouses;
    }
}
