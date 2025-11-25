package tzjanosi.y2017.day22.part2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {
    @Test
    void stepsTest() {
        ReadData readData = new ReadData("testInput.txt");
        Grid map = new Grid(readData.getOutput());
        assertEquals(26, map.steps(100));
        map = new Grid(readData.getOutput());
        assertEquals(2511944, map.steps(10000000));
    }

    @Test
    void stepsProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Grid map = new Grid(readData.getOutput());
        assertEquals(2511456, map.steps(10000000));
    }


}