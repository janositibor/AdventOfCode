package tzjanosi_temp.y2015.day18.part1;


import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Grid {
    private Map<Coordinate, Bulb> bulbs = new ConcurrentHashMap<>();
    private Coordinate limit;

    public Grid(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            limit = new Coordinate(line.length() - 1, input.size() - 1);
            for (int j = 0; j < line.length(); j++) {
                Coordinate coordinate = new Coordinate(j, i);
//                coordinate.setLimit(size);
                Bulb bulb = new Bulb();
                if (line.charAt(j) == '#') {
                    bulb.turnOn();
                }
                bulbs.put(coordinate, bulb);
            }
        }
    }

    public Grid(Grid original) {
        bulbs = new ConcurrentHashMap<>();
        for (Map.Entry<Coordinate, Bulb> entry : original.getBulbs().entrySet()) {
            bulbs.put(new Coordinate(entry.getKey().getX(), entry.getKey().getY()), new Bulb(entry.getValue().isOn()));
        }
        limit = new Coordinate(original.limit.getX(), original.limit.getY());
    }

    private boolean hasToToggle(int numberOfActiveBulbInArea, boolean on) {
        int activeNeighbours = numberOfActiveBulbInArea;
        if (on) {
            activeNeighbours--;
            return activeNeighbours < 2 || activeNeighbours > 3;
        }
        return activeNeighbours == 3;
    }

    public boolean hasToToggle(Coordinate coordinate, boolean on) {
        Coordinate from = createFrom(coordinate);
        Coordinate to = createTo(coordinate);
        int numberOfActiveBulbInArea = numberOfActiveBulbInArea(from, to);
        return hasToToggle(numberOfActiveBulbInArea, on);
    }

    private Coordinate createFrom(Coordinate center) {
        int x = Math.max(center.getX() - 1, 0);
        int y = Math.max(center.getY() - 1, 0);
        return new Coordinate(x, y);
    }

    private Coordinate createTo(Coordinate center) {
        int x = Math.min(center.getX() + 1, limit.getX());
        int y = Math.min(center.getY() + 1, limit.getY());
        return new Coordinate(x, y);
    }

    public int numberOfActiveBulbInArea(Coordinate from, Coordinate to) {
        return (int) bulbs.entrySet()
                .stream()
                .filter((Map.Entry<Coordinate, Bulb> e) -> e.getKey().isInTheArea(from, to))
                .filter((Map.Entry<Coordinate, Bulb> e) -> e.getValue().isOn())
                .count();
    }

    public int countActiveBulbs() {
        return (int) bulbs.values().stream().filter(Bulb::isOn).count();
    }

    public Map<Coordinate, Bulb> getBulbs() {
        return bulbs;
    }

    public Coordinate getLimit() {
        return limit;
    }

    @Override
    public String toString() {
        return "Grid{" +
                "bulbs=" + bulbs +
                ", limit=" + limit +
                '}';
    }
}
