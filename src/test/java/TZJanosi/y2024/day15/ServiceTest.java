package TZJanosi.y2024.day15;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    @Test
    void basicTest() {
        ReadData readData = new ReadData("testInput.txt");
        Service service = new Service(readData.getOutput());
        service.moveAll(false);
        System.out.println(service.getSumOfGPS());
        assertEquals(2028,service.getSumOfGPS());
    }
    @Test
    void basic2Test() {
        ReadData readData = new ReadData("testInput2.txt");
        Service service = new Service(readData.getOutput());
        service.moveAll(false);
        System.out.println(service.getSumOfGPS());
        assertEquals(10092,service.getSumOfGPS());
    }
    @Test
    void problemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Service service = new Service(readData.getOutput());
        service.moveAll(false);
        System.out.println(service.getSumOfGPS());
        assertEquals(1526673,service.getSumOfGPS());
    }
}