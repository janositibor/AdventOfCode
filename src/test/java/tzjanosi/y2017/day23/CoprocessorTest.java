package tzjanosi.y2017.day23;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoprocessorTest {
    @Test
    void execTest() {
        ReadData readData = new ReadData("input.txt");
        Coprocessor coprocessor = new Coprocessor(readData.getOutput());
        assertEquals(6241, coprocessor.execute());
    }

}