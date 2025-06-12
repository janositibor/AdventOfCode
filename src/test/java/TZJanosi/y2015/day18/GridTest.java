package TZJanosi.y2015.day18;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GridTest {
    @Test
    void build() {
        ReadData readData = new ReadData("testInput.txt");
        Grid grid = new Grid(readData.getOutput());
        assertThat(grid.getBulbs())
                .hasSize(36)
                .contains(Map.entry(new Coordinate(0, 0), new Bulb(false)),
                        Map.entry(new Coordinate(0, 2), new Bulb(true)),
                        Map.entry(new Coordinate(2, 0), new Bulb(false)),
                        Map.entry(new Coordinate(0, 4), new Bulb(true)),
                        Map.entry(new Coordinate(3, 1), new Bulb(true)),
                        Map.entry(new Coordinate(5, 5), new Bulb(false))
                );

        assertEquals(new Coordinate(5, 5), grid.getLimit());

    }

    @Test
    void numberOfActiveBulbInArea() {
        Coordinate from;
        Coordinate to;

        ReadData readData = new ReadData("testInput.txt");
        Grid grid = new Grid(readData.getOutput());
        from = new Coordinate(0, 0);
        to = new Coordinate(5, 5);
        assertEquals(15, grid.numberOfActiveBulbInArea(from, to));

        from = new Coordinate(0, 0);
        to = new Coordinate(1, 0);
        assertEquals(1, grid.numberOfActiveBulbInArea(from, to));

        from = new Coordinate(1, 2);
        to = new Coordinate(3, 4);
        assertEquals(2, grid.numberOfActiveBulbInArea(from, to));

        from = new Coordinate(1, 1);
        to = new Coordinate(3, 3);
        assertEquals(2, grid.numberOfActiveBulbInArea(from, to));
    }

    @Test
    void hasToToggle() {
        Coordinate coordinate;
        ReadData readData = new ReadData("testInput.txt");
        Grid grid = new Grid(readData.getOutput());

        coordinate = new Coordinate(0, 0);
        assertEquals(false, grid.getBulbs().get(coordinate).isOn());

        coordinate = new Coordinate(1, 0);
        assertEquals(true, grid.getBulbs().get(coordinate).isOn());

        coordinate = new Coordinate(2, 3);
        assertEquals(true, grid.getBulbs().get(coordinate).isOn());

        coordinate = new Coordinate(5, 5);
        assertEquals(false, grid.getBulbs().get(coordinate).isOn());
    }

    @Test
    void countActiveBulbs() {
        ReadData readData = new ReadData("testInput.txt");
        Grid grid = new Grid(readData.getOutput());
        assertEquals(15, grid.countActiveBulbs());
    }
}