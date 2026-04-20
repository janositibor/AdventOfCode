package tzjanosi.y2018.day16;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    @Test
    void numberOfValidRulesTest() {
        ReadData readData = new ReadData("testInput.txt");
        Service service = new Service(readData.getOutput());
        assertEquals(1, service.numberOfValidRules());
    }

    @Test
    void numberOfValidRulesProblemDataTest() {
        ReadData readData = new ReadData("input_p1.txt");
        Service service = new Service(readData.getOutput());
        assertEquals(636, service.numberOfValidRules());
    }

}