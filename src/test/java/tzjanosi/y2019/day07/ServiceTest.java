package tzjanosi.y2019.day07;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    public static List<Arguments> findMaxThrusterTest() {
        return List.of(
                Arguments.of("3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0", 43210),
                Arguments.of("3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0", 54321),
                Arguments.of("3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0", 65210)
        );
    }

    @ParameterizedTest
    @MethodSource
    void findMaxThrusterTest(String input, long expected) {
        Service service = new Service(input);
        assertEquals(expected, service.findMaxThruster());
    }

    @Test
    void findMaxThrusterProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Service service = new Service(readData.getOutput().get(0));
        assertEquals(844468, service.findMaxThruster());
    }

}