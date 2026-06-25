package tzjanosi.y2019.day05;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    @Test
    void runTest() {
        Service service = new Service(1, "3,0,4,0,99");
        assertEquals(1, service.run());
    }

    @Test
    void runProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Service service = new Service(1, readData.getOutput().get(0));
        assertEquals(5821753, service.run());
    }


}