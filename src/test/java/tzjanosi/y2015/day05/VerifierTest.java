package tzjanosi.y2015.day05;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VerifierTest {
    @Test
    void countNiceStrings() {
        ReadData readData = new ReadData("input.txt");
        Verifier verifier = new Verifier(readData.getOutput());
        assertEquals(238, verifier.countNiceStrings());
    }

    @Test
    void countNiceStringsPart2() {
        ReadData readData = new ReadData("input.txt");
        Verifier verifier = new Verifier(readData.getOutput());
        assertEquals(69, verifier.countNiceStringsPart2());
    }

}