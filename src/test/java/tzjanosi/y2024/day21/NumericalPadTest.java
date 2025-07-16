package tzjanosi.y2024.day21;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class NumericalPadTest {
    @Test
    void testNext() {
        NumericalPad start = new NumericalPad("A");
        start.setWays(Set.of(""));
        NumericalPad next = start.next("0");
        assertThat(next.getWays()).containsExactly("<A");
        NumericalPad nextNext = next.next("2");
        assertThat(nextNext.getWays()).containsExactly("<A^A");
        NumericalPad nextNextNext = nextNext.next("9");
        assertThat(nextNextNext.getWays())
                .hasSize(3)
                .contains("<A^A>^^A");
        NumericalPad last = nextNextNext.next("A");
        assertThat(last.getWays())
                .hasSize(3)
                .contains("<A^A>^^AvvvA");
    }

}