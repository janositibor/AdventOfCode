package tzjanosi.y2018.day23;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    @Test
    void calculateNanobotsNearByTheStrongestTest() {
        ReadData readData = new ReadData("testInput.txt");
        Service service = new Service(readData.getOutput());
        assertEquals(7, service.calculateNanobotsNearByTheStrongest());
    }

    @Test
    void calculateNanobotsNearByTheStrongestProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Service service = new Service(readData.getOutput());
        assertEquals(652, service.calculateNanobotsNearByTheStrongest());
    }

}