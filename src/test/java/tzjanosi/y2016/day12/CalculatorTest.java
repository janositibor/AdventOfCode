package tzjanosi.y2016.day12;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    @Test
    void executeTest() {
        ReadData readData = new ReadData("testInput.txt");
        Calculator calculator = new Calculator(readData.getOutput());
        assertEquals(42, calculator.execute());
    }

    @Test
    void executeProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Calculator calculator = new Calculator(readData.getOutput());
        assertEquals(318083, calculator.execute());
    }

}