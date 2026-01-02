package tzjanosi.y2025.day12;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FarmTest {
    @Test
    void zeroCheckTest() {
        ReadData readData = new ReadData("input.txt");
        Farm farm = new Farm(readData.getOutput());
        assertEquals(517, farm.zeroCheck());
    }

}