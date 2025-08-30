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

    static final Stream<Arguments> stretchTest() {
        return Stream.of(
                Arguments.of(1, 2),
                Arguments.of(2, 4),
                Arguments.of(4, 5),
                Arguments.of(5, 10),
                Arguments.of(10, 11),
                Arguments.of(11, 23),
                Arguments.of(23, 25),
                Arguments.of(25, 26),
                Arguments.of(26, 54),
                Arguments.of(54, 57),
                Arguments.of(57, 59),
                Arguments.of(59, 122),
                Arguments.of(747, 806)
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

    @ParameterizedTest
    @MethodSource
    void stretchTest(int input, int expectedResult) {
        Spiral spiral = new Spiral();
        assertEquals(expectedResult, spiral.stretch(input));
    }

    @Test
    void stretchProblemDataTest() {
        Spiral spiral = new Spiral();
        assertEquals(363010, spiral.stretch(361527));
    }

}