package tzjanosi.y2017.day08;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProcessorTest {
    @Test
    void runTest() {
        ReadData readData = new ReadData("testInput.txt");
        Processor processor = new Processor(readData.getOutput());
        assertEquals(1, processor.run());
    }

    @Test
    void runProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Processor processor = new Processor(readData.getOutput());
        assertEquals(5752, processor.run());
    }

    @Test
    void runPart2Test() {
        ReadData readData = new ReadData("testInput.txt");
        Processor processor = new Processor(readData.getOutput());
        assertEquals(10, processor.runPart2());
    }

    @Test
    void runPart2ProgramDataTest() {
        ReadData readData = new ReadData("input.txt");
        Processor processor = new Processor(readData.getOutput());
        assertEquals(6366, processor.runPart2());
    }
}