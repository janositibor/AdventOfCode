package tzjanosi_temp.y2015.day06.part1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NetworkTest {
    @Test
    void build() {
        ReadData readData = new ReadData("testInput0.txt");
        Network network = new Network(1000, readData.getOutput());
        assertEquals(0, network.countActiveBulbs());
    }

    @Test
    void test0() {
        ReadData readData = new ReadData("testInput0.txt");
        Network network = new Network(1000, readData.getOutput());
        assertEquals(0, network.countActiveBulbs());
        network.run();
        assertEquals(1000 * 1000, network.countActiveBulbs());
    }

    @Test
    void test1() {
        ReadData readData = new ReadData("testInput1.txt");
        Network network = new Network(1000, readData.getOutput());
        assertEquals(0, network.countActiveBulbs());
        network.run();
        assertEquals(999 * 1000, network.countActiveBulbs());
    }

    @Test
    void test2() {
        ReadData readData = new ReadData("testInput2.txt");
        Network network = new Network(1000, readData.getOutput());
        assertEquals(0, network.countActiveBulbs());
        network.run();
        assertEquals(999 * 1000 - 4, network.countActiveBulbs());
    }

    @Test
    void problemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Network network = new Network(1000, readData.getOutput());
        assertEquals(0, network.countActiveBulbs());
        network.run();
        assertEquals(377891, network.countActiveBulbs());
    }

}