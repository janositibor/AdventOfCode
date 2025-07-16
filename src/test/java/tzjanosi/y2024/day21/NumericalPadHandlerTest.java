package tzjanosi.y2024.day21;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NumericalPadHandlerTest {
    @Test
    void testCode() {
        NumericalPadHandler numericalPadHandler = new NumericalPadHandler("029A");
        assertThat(numericalPadHandler.code()).contains("<A^A>^^AvvvA", "<A^A^>^AvvvA", "<A^A^^>AvvvA");
    }

    @Test
    void testCodeExcludeInValidWays() {
        NumericalPadHandler numericalPadHandler = new NumericalPadHandler("010");
        assertThat(numericalPadHandler.code()).containsExactly("<A^<A>vA");
        numericalPadHandler = new NumericalPadHandler("01A");
        assertThat(numericalPadHandler.code()).contains("<A^<A>>vA", "<A^<A>v>A");
    }

    @Test
    void testDeCode() {
        NumericalPadHandler numericalPadHandler = new NumericalPadHandler("<A^A>^^AvvvA");
        assertEquals("029A", numericalPadHandler.deCode());
    }

}