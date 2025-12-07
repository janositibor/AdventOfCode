package tzjanosi.y2025.day07;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SplitterTest {
    @Test
    void splitTest() {
        ReadData readData = new ReadData("testInput.txt");
        Splitter splitter = new Splitter(readData.getOutput());
        assertEquals(21, splitter.split());
    }

    @Test
    void splitProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Splitter splitter = new Splitter(readData.getOutput());
        assertEquals(1642, splitter.split());
    }

    @Test
    void countTimeLinesTest() {
        ReadData readData = new ReadData("testInput.txt");
        Splitter splitter = new Splitter(readData.getOutput());
        assertEquals(40, splitter.countTimeLines());
    }

    @Test
    void countTimeLinesProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Splitter splitter = new Splitter(readData.getOutput());
        assertEquals(47274292756692L, splitter.countTimeLines());
    }

}