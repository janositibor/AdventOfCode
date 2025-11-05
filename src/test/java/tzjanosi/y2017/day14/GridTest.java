package tzjanosi.y2017.day14;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    @Test
    void countUsedTest() {
        ReadData readData = new ReadData("testInput.txt");
        Grid grid = new Grid(readData.getOutput().get(0));
        assertEquals(8108, grid.countUsed());
    }

    @Test
    void countRegionsTest() {
        ReadData readData = new ReadData("testInput.txt");
        Grid grid = new Grid(readData.getOutput().get(0));
        assertEquals(1242, grid.countRegions());
    }

    @Test
    void countUsedProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Grid grid = new Grid(readData.getOutput().get(0));
        assertEquals(8190, grid.countUsed());
    }

    @Test
    void countRegionsProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Grid grid = new Grid(readData.getOutput().get(0));
        assertEquals(1134, grid.countRegions());
    }

}