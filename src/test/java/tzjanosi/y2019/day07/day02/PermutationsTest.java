package tzjanosi.y2019.day07.day02;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PermutationsTest {
    @Test
    void permutationsTest() {
        List<Integer> original = Arrays.asList(1, 2, 3);
        Permutations permutations = new Permutations<>(original);
        assertThat(permutations.permutations())
                .hasSize(6)
                .contains(List.of(1, 2, 3), List.of(1, 3, 2), List.of(2, 1, 3), List.of(2, 3, 1), List.of(3, 1, 2), List.of(3, 2, 1));

        original = Arrays.asList(5, 6, 7, 8, 9);
        permutations = new Permutations<>(original);
        assertThat(permutations.permutations())
                .hasSize(120)
                .contains(List.of(5, 6, 7, 8, 9), List.of(6, 5, 7, 8, 9), List.of(5, 6, 8, 7, 9), List.of(5, 6, 7, 9, 8), List.of(9, 6, 7, 8, 5), List.of(9, 8, 7, 6, 5));
    }

}