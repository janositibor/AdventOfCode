package tzjanosi.y2017.day22;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {
    @Test
    void stepsTest() {
        ReadData readData = new ReadData("testInput.txt");
        Map map = new Map(readData.getOutput());
        assertEquals(5, map.steps(7));
        map = new Map(readData.getOutput());
        assertEquals(41, map.steps(70));
        map = new Map(readData.getOutput());
        assertEquals(5587, map.steps(10000));
    }

    @Test
    void stepsProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Map map = new Map(readData.getOutput());
        assertEquals(5223, map.steps(10000));
    }

}