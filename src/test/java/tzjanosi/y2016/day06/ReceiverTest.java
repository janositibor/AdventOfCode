package tzjanosi.y2016.day06;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReceiverTest {
    @Test
    void test() {
        ReadData readData = new ReadData("testInput.txt");
        Receiver receiver = new Receiver(readData.getOutput());
        assertEquals("easter", receiver.getMessage());
    }

    @Test
    void problemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Receiver receiver = new Receiver(readData.getOutput());
        assertEquals("wkbvmikb", receiver.getMessage());
    }

    @Test
    void testPart2() {
        ReadData readData = new ReadData("testInput.txt");
        Receiver receiver = new Receiver(readData.getOutput());
        assertEquals("advent", receiver.getMessagePart2());
    }

    @Test
    void problemDataPart2Test() {
        ReadData readData = new ReadData("input.txt");
        Receiver receiver = new Receiver(readData.getOutput());
        assertEquals("evakwaga", receiver.getMessagePart2());
    }

}