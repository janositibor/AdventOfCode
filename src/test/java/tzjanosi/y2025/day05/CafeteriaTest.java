package tzjanosi.y2025.day05;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CafeteriaTest {
    @Test
    void numberOfIngredientsInRangesTest() {
        ReadData readData = new ReadData("testInput.txt");
        Cafeteria cafeteria = new Cafeteria(readData.getOutput());
        assertEquals(3, cafeteria.numberOfIngredientsInRanges());
    }

    @Test
    void numberOfIngredientsInRangesProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Cafeteria cafeteria = new Cafeteria(readData.getOutput());
        assertEquals(517, cafeteria.numberOfIngredientsInRanges());
    }

}