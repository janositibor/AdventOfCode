package TZJanosi.y2015.day06.part2;

import java.util.Map;
import java.util.TreeMap;

public class Grid {
    private int size;
    private Map<Coordinate, Bulb> bulbs = new TreeMap<>();

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
                output += bulb.getIntensity();
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
