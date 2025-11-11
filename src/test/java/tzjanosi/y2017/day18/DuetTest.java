package tzjanosi.y2017.day18;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DuetTest {
    @Test
    void executeTest() {
        ReadData readData = new ReadData("testInput.txt");
        Duet duet = new Duet(readData.getOutput());
        assertEquals(4, duet.execute());
    }

    @Test
    void executeProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Duet duet = new Duet(readData.getOutput());
        assertEquals(2951, duet.execute());
    }
}