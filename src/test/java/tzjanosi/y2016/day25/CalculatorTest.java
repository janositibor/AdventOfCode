package tzjanosi.y2016.day25;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    @Test
    void execute() {
        ReadData readData = new ReadData("input.txt");
        Calculator calculator = new Calculator(readData.getOutput());
        assertEquals(182, calculator.execute());
    }

}