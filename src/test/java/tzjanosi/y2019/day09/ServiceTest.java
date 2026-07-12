package tzjanosi.y2019.day09;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    public static Stream<Arguments> inheritedRunPart2Test() {
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

    public static Stream<Arguments> runConveyorBeltTest() {
        return Stream.of(
                Arguments.of("109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99", List.of(109L, 1L, 204L, -1L, 1001L, 100L, 1L, 100L, 1008L, 100L, 16L, 101L, 1006L, 101L, 0L, 99L)),
                Arguments.of("1102,34915192,34915192,7,4,7,99,0", List.of(1219070632396864L)),
                Arguments.of("104,1125899906842624,99", List.of(1125899906842624L))
        );
    }

    @Test
    void inheritedRunTest() {
        Service service = new Service(1, "3,0,4,0,99");
        assertEquals(1, service.run());
    }

    @Test
    void inheritedRunProblemDataTest() {
        ReadData readData = new ReadData("inheritedInput.txt");
        Service service = new Service(1, readData.getOutput().get(0));
        assertEquals(5821753, service.run());
    }

    @ParameterizedTest
    @MethodSource
    void inheritedRunPart2Test(int input, int expected) {
        ReadData readData = new ReadData("inheritedTestInput.txt");
        Service service = new Service(input, readData.getOutput().get(0));
        assertEquals(expected, service.run());
    }

    @Test
    void inheritedRunPart2ProblemDataTest() {
        ReadData readData = new ReadData("inheritedInput.txt");
        Service service = new Service(5, readData.getOutput().get(0));
        assertEquals(11956381, service.run());
    }

    @ParameterizedTest
    @MethodSource
    void runConveyorBeltTest(String input, List<Long> expected) {
        Service service = new Service(0, input);
        assertEquals(expected, service.runConveyorBelt());
    }

    @Test
    void runProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Service service = new Service(1, readData.getOutput().get(0));
        assertEquals(2932210790L, service.runConveyorBelt().get(0));
    }

    @Test
    void runProblemDataPart2Test() {
        ReadData readData = new ReadData("input.txt");
        Service service = new Service(2, readData.getOutput().get(0));
        assertEquals(73144L, service.runConveyorBelt().get(0));
    }


}