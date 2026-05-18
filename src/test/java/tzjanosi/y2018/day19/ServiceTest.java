package tzjanosi.y2018.day19;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    @Test
    void executeTest() {
        ReadData readData = new ReadData("testInput.txt");
        Service service = new Service(readData.getOutput(), 0);
        assertEquals(6, service.execute());
    }

    @Test
    void executeProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Service service = new Service(readData.getOutput(), 0);
        assertEquals(960, service.execute());
    }

    @Test
    void findSumOfDividersProblemDataPartTest() {
        ReadData readData = new ReadData("input.txt");
        Service service = new Service(readData.getOutput(), 1);
        assertEquals(10750428, service.findSumOfDividers(10551293));
        assertEquals(10750428, service.findSumOfDividers(11 * 19 * 2 * 2 + 22 * 2 + 13 + 32 * 14 * 30 * (27 * 28 + 29)));
    }

}