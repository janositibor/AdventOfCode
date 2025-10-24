package tzjanosi.y2017.day13;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FirewallTest {
    @Test
    void calculateTotalSeverityTest() {
        ReadData readData = new ReadData("testInput.txt");
        Firewall firewall = new Firewall(readData.getOutput());
        assertEquals(24, firewall.calculateTotalSeverity());
    }

    @Test
    void calculateTotalSeverityProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Firewall firewall = new Firewall(readData.getOutput());
        assertEquals(1704, firewall.calculateTotalSeverity());
    }

}