package tzjanosi.y2018.day06;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChronalMapTest {
    @Test
    void greatestAreaTest() {
        ReadData readData = new ReadData("testInput.txt");
        ChronalMap chronalMap = new ChronalMap(readData.getOutput());
        assertEquals(17, chronalMap.greatestArea());
    }

    @Test
    void greatestAreaProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        ChronalMap chronalMap = new ChronalMap(readData.getOutput());
        assertEquals(3882, chronalMap.greatestArea());
    }

    @Test
    void greatestAreaTest2() {
        ReadData readData = new ReadData("testInput2.txt");
        ChronalMap chronalMap = new ChronalMap(readData.getOutput());
        assertEquals(5, chronalMap.greatestArea());
//        chronalMap.draw();
    }

    @Test
    void safeAreaTest() {
        ReadData readData = new ReadData("testInput.txt");
        ChronalMap chronalMap = new ChronalMap(readData.getOutput());
        assertEquals(16, chronalMap.safeArea(32));
    }

    @Test
    void safeAreaProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        ChronalMap chronalMap = new ChronalMap(readData.getOutput());
        assertEquals(43852, chronalMap.safeArea(10000));
    }
}