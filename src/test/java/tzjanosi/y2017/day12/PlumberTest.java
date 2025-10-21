package tzjanosi.y2017.day12;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlumberTest {
    @Test
    void countClusterZeroTest() {
        ReadData readData = new ReadData("testInput.txt");
        Plumber plumber = new Plumber(readData.getOutput());
        assertEquals(6, plumber.countClusterZero());
    }

    @Test
    void countClustersTest() {
        ReadData readData = new ReadData("testInput.txt");
        Plumber plumber = new Plumber(readData.getOutput());
        assertEquals(2, plumber.countClusters());
    }

    @Test
    void countClusterZeroProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Plumber plumber = new Plumber(readData.getOutput());
        assertEquals(152, plumber.countClusterZero());
    }

    @Test
    void countClustersProblemDataTestZero() {
        ReadData readData = new ReadData("input.txt");
        Plumber plumber = new Plumber(readData.getOutput());
        assertEquals(186, plumber.countClusters());
    }

}