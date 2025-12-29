package tzjanosi.y2025.day10.part2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FactoryTest {
    @Test
    void findVoltageForAllMachineTest() {
        ReadData readData = new ReadData("testInput.txt");
        Factory factory = new Factory(readData.getOutput());
        assertEquals(33, factory.findVoltageForAllMachine());
    }

    @Test
    void findVoltageForAllMachineProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Factory factory = new Factory(readData.getOutput());
        assertEquals(18105, factory.findVoltageForAllMachine());
    }
}