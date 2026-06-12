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

    @Test
    void findShortestWayTest() {
        ReadData readData = new ReadData("testInput.txt");
        Cave cave = new Cave(readData.getOutput());
        assertEquals(45, cave.findShortestWay());
    }

    @Test
    void findShortestWay2Test() {
        ReadData readData = new ReadData("testInput2.txt");
        Cave cave = new Cave(readData.getOutput());
        assertEquals(23, cave.findShortestWay());

    }

    @Test
    void findShortestWayProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Cave cave = new Cave(readData.getOutput());
        assertEquals(1043, cave.findShortestWay());
    }
}