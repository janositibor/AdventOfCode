package tzjanosi.y2017.day24;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HandlerTest {
    @Test
    void buildTest() {
        ReadData readData = new ReadData("testInput.txt");
        Handler handler = new Handler(readData.getOutput());
        handler.build();
        assertEquals(31, handler.getMaxStrength());
    }

    @Test
    void buildProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Handler handler = new Handler(readData.getOutput());
        handler.build();
        assertEquals(1695, handler.getMaxStrength());
    }

    @Test
    void buildPart2Test() {
        ReadData readData = new ReadData("testInput.txt");
        Handler handler = new Handler(readData.getOutput());
        handler.build();
        assertEquals(19, handler.getLocalMaxStrength());
    }

    @Test
    void buildProblemDataPart2Test() {
        ReadData readData = new ReadData("input.txt");
        Handler handler = new Handler(readData.getOutput());
        handler.build();
        assertEquals(1673, handler.getLocalMaxStrength());
    }

}