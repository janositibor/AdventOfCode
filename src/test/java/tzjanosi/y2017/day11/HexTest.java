package tzjanosi.y2017.day11;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class HexTest {
    private static Stream<Arguments> moveTest() {
        return Stream.of(
                Arguments.of("ne,ne,ne", 3),
                Arguments.of("ne,ne,sw,sw", 0),
                Arguments.of("ne,ne,s,s", 2),
                Arguments.of("se,sw,se,sw,sw", 3),
                Arguments.of("se,n,n", 2)
        );
    }

    @ParameterizedTest
    @MethodSource
    void moveTest(String input, int expected) {
        Hex hex = new Hex(input);
        assertEquals(expected, hex.move());
    }

    @Test
    void moveWithProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Hex hex = new Hex(readData.getOutput().get(0));
        assertEquals(715, hex.move());
    }
}