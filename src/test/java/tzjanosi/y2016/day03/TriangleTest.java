package tzjanosi.y2016.day03;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {
    private static Stream<Arguments> validationTest() {
        return Stream.of(Arguments.of(1, 1, 2, false),
                Arguments.of(1, 2, 2, true),
                Arguments.of(2, 1, 1, false),
                Arguments.of(5, 10, 25, false),
                Arguments.of(25, 10, 5, false));
    }

    @ParameterizedTest
    @MethodSource
    void validationTest(int a, int b, int c, boolean valid) {
        Triangle triangle = new Triangle(a, b, c);
        assertEquals(valid, triangle.isValid());
    }

    @Test
    void test() {
        assertEquals(1, 1);
    }

}