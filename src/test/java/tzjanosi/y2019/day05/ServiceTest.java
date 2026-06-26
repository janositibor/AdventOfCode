package tzjanosi.y2019.day05;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    public static Stream<Arguments> runPart2Test() {
        return Stream.of(
                Arguments.of(-1, 999),
                Arguments.of(0, 999),
                Arguments.of(5, 999),
                Arguments.of(6, 999),
                Arguments.of(7, 999),
                Arguments.of(8, 1000),
                Arguments.of(9, 1001),
                Arguments.of(10, 1001),
                Arguments.of(11, 1001),
                Arguments.of(101, 1001),
                Arguments.of(1001, 1001)
        );
    }

    @Test
    void runTest() {
        Service service = new Service(1, "3,0,4,0,99");
        assertEquals(1, service.run());
    }

    @Test
    void runProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Service service = new Service(1, readData.getOutput().get(0));
        assertEquals(5821753, service.run());
    }

    @ParameterizedTest
    @MethodSource
    void runPart2Test(int input, int expected) {
        ReadData readData = new ReadData("testInput.txt");
        Service service = new Service(input, readData.getOutput().get(0));
        assertEquals(expected, service.run());
    }

    @Test
    void runPart2ProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Service service = new Service(5, readData.getOutput().get(0));
        assertEquals(11956381, service.run());
    }


}