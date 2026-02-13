package tzjanosi.y2018.day02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagementTest {
    @Test
    void manageTest() {
        ReadData readData = new ReadData("testInput.txt");
        Management management = new Management(readData.getOutput());
        assertEquals(12, management.manage());
    }

    @Test
    void manageProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Management management = new Management(readData.getOutput());
        assertEquals(6888, management.manage());
    }

    @Test
    void findSimilarsTest() {
        ReadData readData = new ReadData("testInput2.txt");
        Management management = new Management(readData.getOutput());
        assertEquals("fgij", management.findSimilars());
    }

    @Test
    void findSimilarsProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Management management = new Management(readData.getOutput());
        assertEquals("icxjvbrobtunlelzpdmfkahgs", management.findSimilars());
    }


}