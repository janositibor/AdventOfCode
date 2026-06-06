package tzjanosi.y2018.day21;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    @Test
    void simplifiedExecutionProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Service service = new Service(readData.getOutput(), 0);
        assertEquals(16134795, service.simplifiedExecution());
    }

}