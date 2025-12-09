package tzjanosi.y2025.day09;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FloorTest {
    @Test
    void findMaxAreaTest() {
        ReadData readData = new ReadData("testInput.txt");
        Floor floor = new Floor(readData.getOutput());
        assertEquals(50L, floor.findMaxArea());
    }

    @Test
    void findMaxAreaProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Floor floor = new Floor(readData.getOutput());
        assertEquals(4745816424L, floor.findMaxArea());
    }

}