package tzjanosi.y2016.day20;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FirewallTest {
    @Test
    void findFirstFreeIPTest() {
        ReadData readData = new ReadData("testInput.txt");
        Firewall firewall = new Firewall(readData.getOutput());
        assertEquals(3L, firewall.findFirstFreeIP());
    }

    @Test
    void findFirstFreeIPProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Firewall firewall = new Firewall(readData.getOutput());
        assertEquals(22887907L, firewall.findFirstFreeIP());
    }

}