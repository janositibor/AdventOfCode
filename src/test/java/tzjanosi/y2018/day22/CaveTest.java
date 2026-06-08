package tzjanosi.y2018.day22;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaveTest {
    @Test
    void calculateRiskTest() {
        ReadData readData = new ReadData("testInput.txt");
        Cave cave = new Cave(readData.getOutput());
        assertEquals(114, cave.calculateRisk());
    }

    @Test
    void calculateRiskProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Cave cave = new Cave(readData.getOutput());
        assertEquals(9659, cave.calculateRisk());
    }

}