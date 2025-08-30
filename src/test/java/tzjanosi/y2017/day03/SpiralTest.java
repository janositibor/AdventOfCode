package tzjanosi.y2017.day03;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SpiralTest {
    static final Stream<Arguments> getDistanceTest() {
        return Stream.of(
                Arguments.of(1, 0),
                Arguments.of(2, 1),
                Arguments.of(3, 2),
                Arguments.of(4, 1),
                Arguments.of(5, 2),
                Arguments.of(6, 1),
                Arguments.of(7, 2),
                Arguments.of(8, 1),
                Arguments.of(9, 2),
                Arguments.of(10, 3),
                Arguments.of(12, 3),
                Arguments.of(23, 2),
                Arguments.of(1024, 31)
        );
    }

    @ParameterizedTest
    @MethodSource
    void getDistanceTest(int input, int expectedResult) {
        Spiral spiral = new Spiral();
        assertEquals(expectedResult, spiral.getDistance(input));
    }

    @Test
    void getDistanceProblemDataTest() {
        Spiral spiral = new Spiral();
        assertEquals(326, spiral.getDistance(361527));
    }

}