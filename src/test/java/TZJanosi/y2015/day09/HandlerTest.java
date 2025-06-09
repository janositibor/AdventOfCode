package TZJanosi.y2015.day09;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandlerTest {
    @Test
    void testData() {
        ReadData readData = new ReadData("testInput.txt");
        Handler handler = new Handler(readData.getOutput());
        assertEquals(605, handler.findShortestWay());
    }

    @Test
    void problemData() {
        ReadData readData = new ReadData("input.txt");
        Handler handler = new Handler(readData.getOutput());
        assertEquals(251, handler.findShortestWay());
    }

}