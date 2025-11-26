package tzjanosi.y2017.day23.part2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoprocessorTest {
    @Test
    void execPart2Test() {
        ReadData readData = new ReadData("input.txt");
        Coprocessor coprocessor = new Coprocessor(readData.getOutput());
        assertEquals(909, coprocessor.executePart2());
    }

}