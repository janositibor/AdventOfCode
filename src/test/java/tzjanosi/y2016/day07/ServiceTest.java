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

    @Test
    void calculateNumberOfSSLSupportersTest() {
        ReadData readData = new ReadData("testInput2.txt");
        Service service = new Service(readData.getOutput());
        assertEquals(3, service.calculateNumberOfSSLSupporters());
    }

    @Test
    void calculateNumberOfSSLSupportersProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Service service = new Service(readData.getOutput());
        assertEquals(260, service.calculateNumberOfSSLSupporters());
    }

}