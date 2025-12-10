package tzjanosi.y2025.day10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FactoryTest {
    @Test
    void initAllMachineTest() {
        ReadData readData = new ReadData("testInput.txt");
        Factory factory = new Factory(readData.getOutput());
        assertEquals(7, factory.initAllMachine());
    }

    @Test
    void initAllMachineProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Factory factory = new Factory(readData.getOutput());
        assertEquals(522, factory.initAllMachine());
    }

}