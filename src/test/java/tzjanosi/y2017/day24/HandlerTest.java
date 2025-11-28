package tzjanosi.y2017.day24;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HandlerTest {
    @Test
    void buildTest() {
        ReadData readData = new ReadData("testInput.txt");
        Handler handler = new Handler(readData.getOutput());
        assertEquals(31, handler.build());
    }

    @Test
    void buildProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Handler handler = new Handler(readData.getOutput());
        assertEquals(1695, handler.build());
    }

}