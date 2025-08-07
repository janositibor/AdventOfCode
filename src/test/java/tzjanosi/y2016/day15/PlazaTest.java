package tzjanosi.y2016.day15;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlazaTest {
    @Test
    void findFirstEscapeTimeTest() {
        ReadData readData = new ReadData("testInput.txt");
        Plaza plaza = new Plaza(readData.getOutput());
        assertEquals(5, plaza.findFirstEscapeTime());
    }

    @Test
    void findFirstEscapeTimeProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Plaza plaza = new Plaza(readData.getOutput());
        assertEquals(121834, plaza.findFirstEscapeTime());
    }

    @Test
    void findFirstEscapeTimeWithAdditionalDiskProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Plaza plaza = new Plaza(readData.getOutput());
        assertEquals(3208099, plaza.findFirstEscapeTimeWithAdditionalDisk());
    }

}