package tzjanosi.y2018.day16;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    @Test
    void calculateTest() {
        List<Integer> input = List.of(3, 2, 1, 1);
        Calculator calculator = new Calculator(input);
        assertEquals(List.of(3, 2, 2, 1), calculator.calculate(Operator.MULR, List.of(9, 2, 1, 2)));
        assertEquals(List.of(3, 2, 2, 1), calculator.calculate(Operator.ADDI, List.of(9, 2, 1, 2)));
        assertEquals(List.of(3, 2, 2, 1), calculator.calculate(Operator.SETI, List.of(9, 2, 1, 2)));
        assertNotEquals(List.of(3, 2, 2, 1), calculator.calculate(Operator.ADDR, List.of(9, 2, 1, 2)));
        assertNotEquals(List.of(3, 2, 2, 1), calculator.calculate(Operator.MULI, List.of(9, 2, 1, 2)));
        assertNotEquals(List.of(3, 2, 2, 1), calculator.calculate(Operator.BANR, List.of(9, 2, 1, 2)));
        assertNotEquals(List.of(3, 2, 2, 1), calculator.calculate(Operator.BANI, List.of(9, 2, 1, 2)));
        assertNotEquals(List.of(3, 2, 2, 1), calculator.calculate(Operator.BORR, List.of(9, 2, 1, 2)));
        assertNotEquals(List.of(3, 2, 2, 1), calculator.calculate(Operator.BORI, List.of(9, 2, 1, 2)));
        assertNotEquals(List.of(3, 2, 2, 1), calculator.calculate(Operator.SETR, List.of(9, 2, 1, 2)));
        assertNotEquals(List.of(3, 2, 2, 1), calculator.calculate(Operator.GTIR, List.of(9, 2, 1, 2)));
        assertNotEquals(List.of(3, 2, 2, 1), calculator.calculate(Operator.GTRI, List.of(9, 2, 1, 2)));
        assertNotEquals(List.of(3, 2, 2, 1), calculator.calculate(Operator.GTRR, List.of(9, 2, 1, 2)));
        assertNotEquals(List.of(3, 2, 2, 1), calculator.calculate(Operator.EQIR, List.of(9, 2, 1, 2)));
        assertNotEquals(List.of(3, 2, 2, 1), calculator.calculate(Operator.EQRI, List.of(9, 2, 1, 2)));
        assertNotEquals(List.of(3, 2, 2, 1), calculator.calculate(Operator.EQRR, List.of(9, 2, 1, 2)));
    }

}