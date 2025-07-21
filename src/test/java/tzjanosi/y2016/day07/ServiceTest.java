package tzjanosi.y2016.day07;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    @Test
    void calculateNumberOfTLSSupportersTest() {
        ReadData readData = new ReadData("testInput.txt");
        Service service = new Service(readData.getOutput());
        assertEquals(2, service.calculateNumberOfTLSSupporters());
    }

    @Test
    void calculateNumberOfTLSSupportersProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Service service = new Service(readData.getOutput());
        assertEquals(118, service.calculateNumberOfTLSSupporters());
    }

}