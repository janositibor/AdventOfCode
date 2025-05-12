package TZJanosi.y2024.day22;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SecretNumberGeneratorHandlerTest {
    @Test
    void sum() {
        SecretNumberGeneratorHandler secretNumberGeneratorHandler = new SecretNumberGeneratorHandler(2000);
        assertEquals(37327623L, secretNumberGeneratorHandler.calculateSumOfFinalValues(List.of(1, 10, 100, 2024)));
    }

    @Test
    void sumFromTestFile() {
        ReadData readData = new ReadData("testInput.txt");
        SecretNumberGeneratorHandler secretNumberGeneratorHandler = new SecretNumberGeneratorHandler(2000);
        assertEquals(37327623L, secretNumberGeneratorHandler.calculateSumOfFinalValues(readData.getOutput()));
    }

    @Test
    void sumFromProblemDataFile() {
        ReadData readData = new ReadData("input.txt");
        SecretNumberGeneratorHandler secretNumberGeneratorHandler = new SecretNumberGeneratorHandler(2000);
        assertEquals(17262627539L, secretNumberGeneratorHandler.calculateSumOfFinalValues(readData.getOutput()));
    }
}