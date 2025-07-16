package tzjanosi.y2015.day06.part1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Grid {
    private int size;
    private Map<Coordinate, Bulb> bulbs = new ConcurrentHashMap<>();

    public Grid(int size) {
        this.size = size;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Coordinate coordinate = new Coordinate(i, j);
                coordinate.setLimit(size);
                Bulb bulb = new Bulb();
                bulbs.put(coordinate, bulb);
            }
        }
    }

    public int countActiveBulbs() {
        int output = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Bulb bulb = findBulbAtPosition(i, j);
                if (bulb.isOn()) {
                    output++;
                }
            }
        }
        return output;
    }

    public Bulb findBulbAtPosition(int x, int y) {
        Coordinate coordinate = new Coordinate(x, y);
        coordinate.setLimit(size);
        return bulbs.get(coordinate);
    }

    public Map<Coordinate, Bulb> getBulbs() {
        return bulbs;
    }
}
