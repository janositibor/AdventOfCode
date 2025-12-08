package tzjanosi.y2025.day08;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChristmasDecorationTest {
    @Test
    void decorateTest() {
        ReadData readData = new ReadData("testInput.txt");
        ChristmasDecoration decoration = new ChristmasDecoration(10, readData.getOutput());
        assertEquals(40L, decoration.decorate());
    }

    @Test
    void decorateProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        ChristmasDecoration decoration = new ChristmasDecoration(1000, readData.getOutput());
        assertEquals(66640L, decoration.decorate());
    }

    @Test
    void decorateAllTest() {
        ReadData readData = new ReadData("testInput.txt");
        ChristmasDecoration decoration = new ChristmasDecoration(0, readData.getOutput());
        assertEquals(25272L, decoration.decorateAll());
    }

    @Test
    void decorateAllProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        ChristmasDecoration decoration = new ChristmasDecoration(0, readData.getOutput());
        assertEquals(78894156L, decoration.decorateAll());
    }

}