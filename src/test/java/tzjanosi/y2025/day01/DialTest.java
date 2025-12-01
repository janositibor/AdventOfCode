package tzjanosi.y2025.day01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DialTest {
    @Test
    void calculatePasswordTest() {
        ReadData readData = new ReadData("testInput.txt");
        Dial dial = new Dial(readData.getOutput());
        assertEquals(3, dial.calculatePassword());
    }

    @Test
    void calculatePasswordProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Dial dial = new Dial(readData.getOutput());
        assertEquals(1081, dial.calculatePassword());
    }

    @Test
    void calculatePasswordPart2Test() {
        ReadData readData = new ReadData("testInput.txt");
        Dial dial = new Dial(readData.getOutput());
        assertEquals(6, dial.calculatePasswordPart2());
    }

    @Test
    void calculatePasswordPart2ProgramDataTest() {
        ReadData readData = new ReadData("input.txt");
        Dial dial = new Dial(readData.getOutput());
        assertEquals(6689, dial.calculatePasswordPart2());
    }



}