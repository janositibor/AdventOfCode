package tzjanosi_temp.y2015.day23;

import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ComputerTest {
    @Test
    void init() {
        ReadData readData = new ReadData("testInput.txt");
        Register registerA = new Register("a", 0);
        Computer computer = new Computer(readData.getOutput(), Set.of(registerA, new Register("b", 0)));

        List<Operation> operations = computer.getOperations();

        assertThat(operations)
                .hasSize(4)
                .extracting(Operation::getRegister)
                .containsOnly(registerA);
        assertThat(operations)
                .hasSize(4)
                .extracting(Operation::getAction, Operation::getOffset)
                .containsExactly(Tuple.tuple(Action.INC, 0),
                        Tuple.tuple(Action.JIO, 2),
                        Tuple.tuple(Action.TPL, 0),
                        Tuple.tuple(Action.INC, 0));

    }

    @Test
    void runWithTestData() {
        ReadData readData = new ReadData("testInput.txt");
        Computer computer = new Computer(readData.getOutput(), Set.of(new Register("a", 0), new Register("b", 0)));

        assertEquals(2, computer.run("a"));
    }

    @Test
    void runWithProblemData() {
        ReadData readData = new ReadData("input.txt");
        Computer computer = new Computer(readData.getOutput(), Set.of(new Register("a", 0), new Register("b", 0)));

        assertEquals(184, computer.run("b"));
    }

    @Test
    void runWithProblemDataPart2() {
        ReadData readData = new ReadData("input.txt");
        Computer computer = new Computer(readData.getOutput(), Set.of(new Register("a", 1), new Register("b", 0)));

        assertEquals(231, computer.run("b"));
    }

}