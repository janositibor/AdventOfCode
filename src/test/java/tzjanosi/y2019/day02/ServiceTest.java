package tzjanosi.y2019.day02;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    public static Stream<Arguments> runTest() {
        return Stream.of(
                Arguments.of("1,0,0,0,99", 2),
                Arguments.of("2,3,0,3,99", 2),
                Arguments.of("2,4,4,5,99,0", 2),
                Arguments.of("1,1,1,4,99,5,6,0,99", 30)
        );
    }

    @ParameterizedTest
    @MethodSource
    void runTest(String input, int expected) {
        Service service = new Service(input);
        assertEquals(expected, service.run());
    }

    @Test
    void runWithFileInputTest() {
        ReadData readData = new ReadData("testInput.txt");
        Service service = new Service(readData.getOutput().get(0));
        assertEquals(3500, service.run());
    }

    @Test
    void runProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Service service = new Service(readData.getOutput().get(0));
        service.preparation();
        assertEquals(9581917, service.run());
    }

}