package tzjanosi_temp.y2024.day24.part2;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LogicCircuitTest {
    @Test
    void runWithProblemInput() {
        ReadData readData = new ReadData("input.txt");
        LogicCircuit logicCircuit = new LogicCircuit(readData.getOutput());
        logicCircuit.run();

        assertEquals(60714423975686L, logicCircuit.calculateZValue());
    }

    @Test
    void setInputs() {
        ReadData readData = new ReadData("input.txt");
        LogicCircuit logicCircuit = new LogicCircuit(readData.getOutput());
        logicCircuit.setInputNumbers(0, 0);
        assertThat(logicCircuit.getOperands()
                .stream()
                .filter(o -> o.isValid())
                .filter(o -> o.getType().equals("x"))
                .mapToInt(Operand::getIntValue)
                .max()
                .orElseThrow(() -> new IllegalStateException("Empty operands list!")))
                .isEqualTo(0);

        logicCircuit.setInputNumbers(Math.pow(2, 45) - 1, Math.pow(2, 45) - 1);
        assertThat(logicCircuit.getOperands()
                .stream()
                .filter(o -> o.isValid())
                .filter(o -> o.getType().equals("y"))
                .mapToInt(Operand::getIntValue)
                .min()
                .orElseThrow(() -> new IllegalStateException("Empty operands list!")))
                .isEqualTo(1);
    }
}