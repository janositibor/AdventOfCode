package tzjanosi_temp.y2015.day13;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandlerTest {
    @Test
    void testDataPart1() {
        ReadData readData = new ReadData("testInput.txt");
        Handler handler = new Handler(readData.getOutput());
        assertEquals(330, handler.findHappiestWay());
    }

    @Test
    void problemDataPart1() {
        ReadData readData = new ReadData("input.txt");
        Handler handler = new Handler(readData.getOutput());
        assertEquals(709, handler.findHappiestWay());
    }

    @Test
    void problemDataPart2() {
        ReadData readData = new ReadData("input.txt");
        Handler handler = new Handler(readData.getOutput());
        assertEquals(668, handler.findHappiestWayWithYou());
    }

}