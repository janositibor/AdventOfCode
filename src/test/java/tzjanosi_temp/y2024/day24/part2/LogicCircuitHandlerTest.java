package tzjanosi_temp.y2024.day24.part2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogicCircuitHandlerTest {

    @Test
    void build() {
        ReadData readData = new ReadData("input.txt");
        LogicCircuitHandler handler = new LogicCircuitHandler(readData.getOutput());
    }

    @Test
    void checkCircuit() {
        ReadData readData = new ReadData("input.txt");
        LogicCircuitHandler handler = new LogicCircuitHandler(readData.getOutput());
        handler.checkCircuit();
        assertEquals("cgh,frt,pmd,sps,tst,z05,z11,z23", handler.getSwaps());
    }

    @Test
    void checkCalculator() {
        ReadData readData = new ReadData("input.txt");
        LogicCircuitHandler handler = new LogicCircuitHandler(readData.getOutput());
        handler.checkCircuit();
        assertEquals(0, handler.calculateSum(0, 0));
        assertEquals(11111 + 111111, handler.calculateSum(11111, 111111));
        assertEquals(2 * (Math.pow(2, 45) - 1), handler.calculateSum(Math.pow(2, 45) - 1, Math.pow(2, 45) - 1));
    }
}