package tzjanosi.y2025.day02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IDValidatorTest {
    @Test
    void calculateSumOfInvalidIdsTest() {
        ReadData readData = new ReadData("testInput.txt");
        IDValidator idValidator = new IDValidator(readData.getOutput().get(0));
        assertEquals(1227775554L, idValidator.calculateSumOfInvalidIds());
    }

    @Test
    void calculateSumOfInvalidIdsProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        IDValidator idValidator = new IDValidator(readData.getOutput().get(0));
        assertEquals(24157613387L, idValidator.calculateSumOfInvalidIds());
    }

    @Test
    void calculateSumOfInvalidIdsForPart2Test() {
        ReadData readData = new ReadData("testInput.txt");
        IDValidator idValidator = new IDValidator(readData.getOutput().get(0));
        assertEquals(4174379265L, idValidator.calculateSumOfInvalidIdsForPart2());
    }

    @Test
    void calculateSumOfInvalidIdsForPart2ProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        IDValidator idValidator = new IDValidator(readData.getOutput().get(0));
        assertEquals(33832678380L, idValidator.calculateSumOfInvalidIdsForPart2());
    }
}