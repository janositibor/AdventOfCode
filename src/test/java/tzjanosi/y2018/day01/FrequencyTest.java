package tzjanosi.y2018.day01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FrequencyTest {
    @Test
    void sumOfShiftsTest() {
        ReadData readData = new ReadData("input.txt");
        Frequency frequency = new Frequency(readData.getOutput());
        assertEquals(416, frequency.sumOfShifts());
    }
}