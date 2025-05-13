package TZJanosi.y2024.day22.part2;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SecretNumberGeneratorTest {
    @Test
    void generatorTest_10() {
        SecretNumberGenerator secretNumberGenerator = new SecretNumberGenerator(123, 10);
        assertEquals(5908254, secretNumberGenerator.createFinalValue());
        assertThat(secretNumberGenerator.getPrices())
                .containsExactly(3, 0, 6, 5, 4, 4, 6, 4, 4, 2, 4);
        assertThat(secretNumberGenerator.getChanges())
                .containsExactly(-3, 6, -1, -1, 0, 2, -2, 0, -2, 2);

//        System.out.println(secretNumberGenerator.createAllPricesToChangePatterns());
        assertThat(secretNumberGenerator.createAllPricesToChangePatterns(5).entrySet())
                .hasSize(7)
                .contains(Map.entry(List.of(2, -2, 0, -2), 2),
                        Map.entry(List.of(-1, 0, 2, -2), 4),
                        Map.entry(List.of(-1, -1, 0, 2), 6),
                        Map.entry(List.of(6, -1, -1, 0), 4),
                        Map.entry(List.of(-3, 6, -1, -1), 4)
                );
    }

}