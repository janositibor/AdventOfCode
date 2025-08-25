package tzjanosi.y2016.day23;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    @Test
    void executeOldTestInput0Test() {
        ReadData readData = new ReadData("oldTestInput0.txt");
        Calculator calculator = new Calculator(readData.getOutput());
        assertEquals(42, calculator.execute());
    }

    @Test
    void executeOldTestInput1Test() {
        ReadData readData = new ReadData("oldTestInput1.txt");
        Calculator calculator = new Calculator(readData.getOutput());
        assertEquals(318083, calculator.execute());
    }

    @Test
    void executeOldTestInput2Test() {
        ReadData readData = new ReadData("oldTestInput2.txt");
        Calculator calculator = new Calculator(readData.getOutput());
        assertEquals(9227737, calculator.execute());
    }

    @Test
    void executeTest() {
        ReadData readData = new ReadData("testInput.txt");
        Calculator calculator = new Calculator(readData.getOutput());
        assertEquals(3, calculator.execute());
    }

    @Test
    void executeProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Calculator calculator = new Calculator(readData.getOutput());
        assertEquals(11120, calculator.execute());
    }
}