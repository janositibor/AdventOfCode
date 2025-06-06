package TZJanosi.y2015.day06.part2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NetworkTest {
    @Test
    void problemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Network network = new Network(1000, readData.getOutput());
        assertEquals(0, network.countActiveBulbs());
        network.run();
        assertEquals(14110788, network.countActiveBulbs());
    }

}