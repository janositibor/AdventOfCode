package tzjanosi.y2019.day03;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    public static Stream<Arguments> findNearestCrossingTest() {
        return Stream.of(
                Arguments.of("testInput.txt", 6),
                Arguments.of("testInput2.txt", 159),
                Arguments.of("testInput3.txt", 135)
        );
    }

    @ParameterizedTest
    @MethodSource
    void findNearestCrossingTest(String input, int expected) {
        ReadData readData = new ReadData(input);
        Service service = new Service(readData.getOutput());
        assertEquals(expected, service.findNearestCrossing());
    }

    @Test
    void findNearestCrossingProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Service service = new Service(readData.getOutput());
        assertEquals(446, service.findNearestCrossing());
    }

}