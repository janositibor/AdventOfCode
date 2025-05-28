package TZJanosi.y2024.day24.part1;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.junit.jupiter.api.Assertions.*;

class LogicCircuitTest {
    @Test
    void buildLogicCircuit() {
        ReadData readData = new ReadData("testInput.txt");
        LogicCircuit logicCircuit = new LogicCircuit(readData.getOutput());

        assertThat(logicCircuit.getOperands())
                .hasSize(9);

        assertThat(logicCircuit.getOperands()
                .stream()
                .filter(o -> o.isValid()))
                .hasSize(6);

        assertThat(logicCircuit.getOperands()
                .stream()
                .filter(o -> o.isValid())
                .filter(o -> o.getType().equals("x"))
                .map(o -> o.getOrder()))
                .hasSize(3)
                .containsOnly(0, 1, 2);

        assertThat(logicCircuit.getOperands()
                .stream()
                .filter(o -> o.isValid())
                .filter(o -> o.getType().equals("y"))
                .toList())
                .hasSize(3)
                .extracting(Operand::getOrder, Operand::getValue)
                .containsOnly(tuple(0, false),
                        tuple(1, true),
                        tuple(2, false));

        assertThat(logicCircuit.getOperands()
                .stream()
                .filter(o -> o.getType().equals("z"))
                .anyMatch(o -> o.isValid()))
                .isFalse();

        assertThat(logicCircuit.getOperations())
                .hasSize(3)
                .extracting(o -> o.getOperand1().getName(), o -> o.getOperand2().getName(), Operation::getOperator, o -> o.getResult().getName(), Operation::isDone, Operation::waitForProcess)
                .containsOnly(tuple("x00", "y00", Operator.AND, "z00", false, true),
                        tuple("x01", "y01", Operator.XOR, "z01", false, true),
                        tuple("x02", "y02", Operator.OR, "z02", false, true)
                );
    }

    @Test
    void runWithTestInput() {
        ReadData readData = new ReadData("testInput.txt");
        LogicCircuit logicCircuit = new LogicCircuit(readData.getOutput());
        logicCircuit.run();

        assertThat(logicCircuit.getOperands()
                .stream()
                .filter(o -> o.getType().equals("z"))
                .allMatch(o -> o.isValid()))
                .isTrue();

        assertThat(logicCircuit.getOperands()
                .stream()
                .filter(o -> o.getType().equals("z"))
                .toList())
                .hasSize(3)
                .extracting(Operand::getName, Operand::getValue)
                .containsOnly(tuple("z00", false),
                        tuple("z01", false),
                        tuple("z02", true));

        assertEquals(4L, logicCircuit.calculateZValue());
    }

    @Test
    void runWithTestInput2() {
        ReadData readData = new ReadData("testInput2.txt");
        LogicCircuit logicCircuit = new LogicCircuit(readData.getOutput());
        logicCircuit.run();

        assertEquals(2024L, logicCircuit.calculateZValue());
    }

    @Test
    void runWithProblemInput() {
        ReadData readData = new ReadData("input.txt");
        LogicCircuit logicCircuit = new LogicCircuit(readData.getOutput());
        logicCircuit.run();

        assertEquals(60714423975686L, logicCircuit.calculateZValue());
    }

}