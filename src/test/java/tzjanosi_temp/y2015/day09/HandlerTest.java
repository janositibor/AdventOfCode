package tzjanosi_temp.y2015.day09;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandlerTest {
    @Test
    void testDataPart1() {
        ReadData readData = new ReadData("testInput.txt");
        Handler handler = new Handler(readData.getOutput());
        assertEquals(605, handler.findShortestWay());
    }

    @Test
    void problemDataPart1() {
        ReadData readData = new ReadData("input.txt");
        Handler handler = new Handler(readData.getOutput());
        assertEquals(251, handler.findShortestWay());
    }

    @Test
    void testDataPart2() {
        ReadData readData = new ReadData("testInput.txt");
        Handler handler = new Handler(readData.getOutput());
        assertEquals(982, handler.findLongestWay());
    }

    @Test
    void problemDataPart2() {
        ReadData readData = new ReadData("input.txt");
        Handler handler = new Handler(readData.getOutput());
        assertEquals(898, handler.findLongestWay());
    }

}