package tzjanosi.y2018.day16;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    @Test
    void numberOfValidRulesTest() {
        ReadData readData = new ReadData("testInput.txt");
        ReadData readDataP2 = new ReadData("input_p2.txt");
        Service service = new Service(readData.getOutput(), readDataP2.getOutput());
        assertEquals(1, service.numberOfValidRules());
    }

    @Test
    void numberOfValidRulesProblemDataTest() {
        ReadData readData = new ReadData("input_p1.txt");
        ReadData readDataP2 = new ReadData("input_p2.txt");
        Service service = new Service(readData.getOutput(), readDataP2.getOutput());
        assertEquals(636, service.numberOfValidRules());
    }

    @Test
    void deCryptProblemDataTest() {
        ReadData readData = new ReadData("input_p1.txt");
        ReadData readDataP2 = new ReadData("input_p2.txt");
        Service service = new Service(readData.getOutput(), readDataP2.getOutput());
        assertEquals(674, service.execute());
    }

}