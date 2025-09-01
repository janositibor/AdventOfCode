package tzjanosi.y2017.day07;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CircusTest {
    @Test
    void findRootTest() {
        ReadData readData = new ReadData("testInput.txt");
        Circus circus = new Circus(readData.getOutput());
        assertEquals("tknk", circus.findRoot());
    }

    @Test
    void findRootProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Circus circus = new Circus(readData.getOutput());
        assertEquals("gmcrj", circus.findRoot());
    }

}