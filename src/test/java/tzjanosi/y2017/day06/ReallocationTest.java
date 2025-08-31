package tzjanosi.y2017.day06;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReallocationTest {
    @Test
    void processTest() {
        ReadData readData = new ReadData("testInput.txt");
        Reallocation reallocation = new Reallocation(readData.getOutput().get(0));
        assertEquals(5, reallocation.process());
    }

    @Test
    void processProgramDataTest() {
        ReadData readData = new ReadData("input.txt");
        Reallocation reallocation = new Reallocation(readData.getOutput().get(0));
        assertEquals(5042, reallocation.process());
    }

}