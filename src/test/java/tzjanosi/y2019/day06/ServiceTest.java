package tzjanosi.y2019.day06;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    @Test
    void findTotalNumberOfOrbitsTest() {
        ReadData readData = new ReadData("testInput.txt");
        Service service = new Service(readData.getOutput());
        assertEquals(42, service.findTotalNumberOfOrbits());
    }

    @Test
    void findTotalNumberOfOrbitsProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Service service = new Service(readData.getOutput());
        assertEquals(249308, service.findTotalNumberOfOrbits());
    }


}