package tzjanosi.y2016.day03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    @Test
    void serviceTest() {
        ReadData readData = new ReadData("input.txt");
        Service service = new Service(readData.getOutput());

        assertEquals(982, service.countValidTriangles());
    }

}