package tzjanosi.y2019.day12;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    @Test
    void findPeriodTest1() {
        ReadData readData = new ReadData("testInput1.txt");
        Service service = new Service(readData.getOutput());
        assertEquals(2772, service.findPeriod());
    }

    @Test
    void findPeriodTest2() {
        ReadData readData = new ReadData("testInput2.txt");
        Service service = new Service(readData.getOutput());
        assertEquals(4686774924L, service.findPeriod());
    }

    @Test
    void problemDataPart2Test() {
        ReadData readData = new ReadData("input.txt");
        Service service = new Service(readData.getOutput());
        assertEquals(314610635824376L, service.findPeriod());
    }

    @Test
    void energyTest1() {
        ReadData readData = new ReadData("testInput1.txt");
        Service service = new Service(readData.getOutput());
        service.steps(10);
        assertEquals(179, service.energy());
    }

    @Test
    void energyTest2() {
        ReadData readData = new ReadData("testInput2.txt");
        Service service = new Service(readData.getOutput());
        service.steps(100);
        assertEquals(1940, service.energy());
    }

    @Test
    void problemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Service service = new Service(readData.getOutput());
        service.steps(1000);
        assertEquals(10028, service.energy());
    }

}