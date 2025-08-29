package tzjanosi.y2017.day02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckSumTest {
    @Test
    void calculateCheckSumTest() {
        ReadData readData = new ReadData("testInput.txt");
        CheckSum checkSum = new CheckSum(readData.getOutput());
        assertEquals(18, checkSum.calculateCheckSum());
    }

    @Test
    void calculateCheckSumProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        CheckSum checkSum = new CheckSum(readData.getOutput());
        assertEquals(21845, checkSum.calculateCheckSum());
    }

    @Test
    void calculateCheckSumPart2Test() {
        ReadData readData = new ReadData("testInput2.txt");
        CheckSum checkSum = new CheckSum(readData.getOutput());
        assertEquals(9, checkSum.calculateCheckSumPart2());
    }

    @Test
    void calculateCheckSumProblemDataPart2Test() {
        ReadData readData = new ReadData("input.txt");
        CheckSum checkSum = new CheckSum(readData.getOutput());
        assertEquals(191, checkSum.calculateCheckSumPart2());
    }

}