package TZJanosi.y2015.day07;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.junit.jupiter.api.Assertions.*;

class LogicCircuitTest {
    @Test
    void testInput() {
        ReadData readData = new ReadData("testInput.txt");
        LogicCircuit circuit = new LogicCircuit(readData.getOutput());
        circuit.run();
//        System.out.println(circuit.getOperations());
        assertThat(circuit.getOperands())
                .extracting(Operand::getName, Operand::getValue)
                .hasSize(9)
                .contains(tuple("x", 123),
                        tuple("y", 456),
                        tuple("d", 72),
                        tuple("e", 507),
                        tuple("f", 492),
                        tuple("g", 114),
                        tuple("h", 65412),
                        tuple("i", 65079)
                );
    }

    @Test
    void problemData() {
        ReadData readData = new ReadData("input.txt");
        LogicCircuit circuit = new LogicCircuit(readData.getOutput());
        circuit.run();
        assertEquals(46065, circuit.getResult("a"));
    }


}