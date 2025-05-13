package TZJanosi.y2024.day22.part2;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SecretNumberGeneratorHandlerTest {
    @Test
    void sumPricesAndChangesFromTestFile() {
        ReadData readData = new ReadData("testInput.txt");
        SecretNumberGeneratorHandler secretNumberGeneratorHandler = new SecretNumberGeneratorHandler(2000);
        assertEquals(37327623L, secretNumberGeneratorHandler.calculateSumOfFinalValues(readData.getOutput()));
        assertThat(secretNumberGeneratorHandler.getGeneratorsWithPrices().keySet())
                .hasSize(4)
                .containsOnly(1, 10, 100, 2024);

        assertThat(secretNumberGeneratorHandler.getGeneratorsWithPrices().get(2024).getPrices())
                .hasSize(2001);
        assertThat(secretNumberGeneratorHandler.getGeneratorsWithPrices().get(2024).getPrices().get(2000))
                .isEqualTo(4);
        assertThat(secretNumberGeneratorHandler.getGeneratorsWithPrices().get(2024).getChanges())
                .hasSize(2000);
    }

    @Test
    void pricesAndChangesFromTest2File() {
        ReadData readData = new ReadData("testInput2.txt");
        SecretNumberGeneratorHandler secretNumberGeneratorHandler = new SecretNumberGeneratorHandler(2000);
        assertEquals(37990510L, secretNumberGeneratorHandler.calculateSumOfFinalValues(readData.getOutput()));
        assertThat(secretNumberGeneratorHandler.getGeneratorsWithPrices().keySet())
                .hasSize(4)
                .containsOnly(1, 2, 3, 2024);

        assertThat(secretNumberGeneratorHandler.getGeneratorsWithPrices().get(2024).getPrices())
                .hasSize(2001);
        assertThat(secretNumberGeneratorHandler.getGeneratorsWithPrices().get(2024).getPrices().get(2000))
                .isEqualTo(4);
        assertThat(secretNumberGeneratorHandler.getGeneratorsWithPrices().get(2024).getChanges())
                .hasSize(2000);

        assertEquals(23, secretNumberGeneratorHandler.calculateGains(3));
    }

    @Test
    void pricesAndChangesFromProblemFile() {
        ReadData readData = new ReadData("input.txt");
        SecretNumberGeneratorHandler secretNumberGeneratorHandler = new SecretNumberGeneratorHandler(2000);
        assertEquals(17262627539L, secretNumberGeneratorHandler.calculateSumOfFinalValues(readData.getOutput()));
        assertEquals(1986, secretNumberGeneratorHandler.calculateGains(3));

    }
}