package TZJanosi.y2024.day24;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OperandTest {
    @Test
    void build() {
        Operand operand;

        operand = new Operand("x00");
        assertThat(operand)
                .hasFieldOrPropertyWithValue("name", "x00")
                .hasFieldOrPropertyWithValue("type", "x")
                .hasFieldOrPropertyWithValue("order", 0)
                .hasFieldOrPropertyWithValue("valid", false);

        operand = new Operand("y29");
        assertThat(operand)
                .hasFieldOrPropertyWithValue("name", "y29")
                .hasFieldOrPropertyWithValue("type", "y")
                .hasFieldOrPropertyWithValue("order", 29)
                .hasFieldOrPropertyWithValue("valid", false);

        operand = new Operand("z09", 1);
        assertThat(operand)
                .hasFieldOrPropertyWithValue("name", "z09")
                .hasFieldOrPropertyWithValue("type", "z")
                .hasFieldOrPropertyWithValue("order", 9)
                .hasFieldOrPropertyWithValue("valid", true)
                .hasFieldOrPropertyWithValue("intValue", 1)
                .hasFieldOrPropertyWithValue("value", true);

        operand = new Operand("x08", 0);
        assertThat(operand)
                .hasFieldOrPropertyWithValue("name", "x08")
                .hasFieldOrPropertyWithValue("type", "x")
                .hasFieldOrPropertyWithValue("order", 8)
                .hasFieldOrPropertyWithValue("valid", true)
                .hasFieldOrPropertyWithValue("intValue", 0)
                .hasFieldOrPropertyWithValue("value", false);

    }

}