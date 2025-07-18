package tzjanosi.y2016.day02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KeyPadHandlerTest {
    @Test
    void testData() {
        ReadData readData = new ReadData("testInput.txt");
        KeyPadHandler keyPadHandler = new KeyPadHandler(readData.getOutput());
        assertEquals("1985", keyPadHandler.getNumbers());
    }

    @Test
    void problemDataTest() {
        ReadData readData = new ReadData("input.txt");
        KeyPadHandler keyPadHandler = new KeyPadHandler(readData.getOutput());
        assertEquals("36629", keyPadHandler.getNumbers());
    }

    @Test
    void testDataPart2() {
        ReadData readData = new ReadData("testInput.txt");
        KeyPadHandler keyPadHandler = new KeyPadHandler(readData.getOutput());
        assertEquals("5DB3", keyPadHandler.getNumbersPart2());
    }

    @Test
    void problemDataTestPart2() {
        ReadData readData = new ReadData("input.txt");
        KeyPadHandler keyPadHandler = new KeyPadHandler(readData.getOutput());
        assertEquals("99C3D", keyPadHandler.getNumbersPart2());
    }

}