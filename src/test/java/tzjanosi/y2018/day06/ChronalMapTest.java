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
        // 5105 is too high
        assertEquals(3882, chronalMap.greatestArea());
    }

    @Test
    void greatestAreaTest2() {
        ReadData readData = new ReadData("testInput2.txt");
        ChronalMap chronalMap = new ChronalMap(readData.getOutput());
        assertEquals(5, chronalMap.greatestArea());
//        chronalMap.draw();
    }

}