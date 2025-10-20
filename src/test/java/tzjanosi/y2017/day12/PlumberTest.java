package tzjanosi.y2017.day12;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlumberTest {
    @Test
    void countClusterTest() {
        ReadData readData = new ReadData("testInput.txt");
        Plumber plumber = new Plumber(readData.getOutput());
        assertEquals(6, plumber.countCluster());
    }

    @Test
    void countClusterProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Plumber plumber = new Plumber(readData.getOutput());
        assertEquals(152, plumber.countCluster());
    }

}