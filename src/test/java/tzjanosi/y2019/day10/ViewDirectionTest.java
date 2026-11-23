package tzjanosi.y2019.day10;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

//@Disabled
class ViewDirectionTest {
    public static Stream<Arguments> calculateAngleTest() {
        return Stream.of(
                Arguments.of(new Coordinate(0, -1), 0 * Math.PI / 4),
                Arguments.of(new Coordinate(1, -2), 0.46364760900080615),
                Arguments.of(new Coordinate(1, -1), 1 * Math.PI / 4),
                Arguments.of(new Coordinate(1, 0), 2 * Math.PI / 4),
                Arguments.of(new Coordinate(1, 1), 3 * Math.PI / 4),
                Arguments.of(new Coordinate(1, 2), Math.PI - 0.46364760900080615),
                Arguments.of(new Coordinate(0, 1), 4 * Math.PI / 4),
                Arguments.of(new Coordinate(-1, 2), Math.PI + 0.46364760900080615),
                Arguments.of(new Coordinate(-1, 1), 5 * Math.PI / 4),
                Arguments.of(new Coordinate(-1, 0), 6 * Math.PI / 4),
                Arguments.of(new Coordinate(-1, -1), 7 * Math.PI / 4),
                Arguments.of(new Coordinate(-1, -2), 2 * Math.PI - 0.46364760900080615)
        );
    }

    @ParameterizedTest
    @MethodSource
    void calculateAngleTest(Coordinate coordinate, double expected) {
        ViewDirection viewDirection = new ViewDirection(coordinate);
        assertEquals(expected, viewDirection.getAngle());
    }

    @Test
    void atanTest() {
        assertEquals(Math.PI / 4, Math.atan(1));
        assertEquals(0.4636476090008061, Math.atan(1.0 / 2.0));
        assertEquals(1.1071487177940904, Math.atan(2.0));
    }

}