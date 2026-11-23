package tzjanosi.y2019.day11;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    @Test
    void problemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Service service = new Service(readData.getOutput().get(0));
        service.run();
        assertEquals(2088, service.numberOfPaintedPoints());
    }

}