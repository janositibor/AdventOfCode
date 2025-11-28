package tzjanosi.y2017.day25;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TuringTest {
    @Test
    void execTest() {
        ReadData readData = new ReadData("testInput.txt");
        Turing turing = new Turing(readData.getOutput());
        assertEquals(3, turing.exec());
    }

    @Test
    void execProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Turing turing = new Turing(readData.getOutput());
        assertEquals(3554, turing.exec());
    }


}