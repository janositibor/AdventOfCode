package tzjanosi.y2017.day05;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CpuTest {
    @Test
    void runTest() {
        ReadData readData = new ReadData("testInput.txt");
        Cpu cpu = new Cpu(readData.getOutput());
        assertEquals(5, cpu.run());
    }

    @Test
    void runProgramDataTest() {
        ReadData readData = new ReadData("input.txt");
        Cpu cpu = new Cpu(readData.getOutput());
        assertEquals(0, cpu.run());
    }

}