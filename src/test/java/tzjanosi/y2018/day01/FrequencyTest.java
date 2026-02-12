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

    @Test
    void firstDuplicateTest() {
        ReadData readData = new ReadData("testInput.txt");
        Frequency frequency = new Frequency(readData.getOutput());
        assertEquals(2, frequency.firstDuplicate());
    }

    @Test
    void firstDuplicateProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Frequency frequency = new Frequency(readData.getOutput());
        assertEquals(56752, frequency.firstDuplicateFast());
    }
}