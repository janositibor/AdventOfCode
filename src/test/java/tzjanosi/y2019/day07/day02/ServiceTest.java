package tzjanosi.y2019.day07.day02;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    public static List<Arguments> findGlobalMaxThrusterTest() {
        return List.of(
                Arguments.of("3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5", 139629729),
                Arguments.of("3,52,1001,52,-5,52,3,53,1,52,56,54,1007,54,5,55,1005,55,26,1001,54,-5,54,1105,1,12,1,53,54,53,1008,54,0,55,1001,55,1,55,2,53,55,53,4,53,1001,56,-1,56,1005,56,6,99,0,0,0,0,10", 18216)
        );
    }

//    @Test
//    void findGlobalMaxThrusterTest(){
//        Service service = new Service("3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5");
//        assertEquals(139629729, service.findGlobalMaxThruster());
//    }

    @ParameterizedTest
    @MethodSource
    void findGlobalMaxThrusterTest(String input, long expected) {
        Service service = new Service(input);
        assertEquals(expected, service.findGlobalMaxThruster());
    }

    @Test
    void findGlobalMaxThrusterProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Service service = new Service(readData.getOutput().get(0));
        assertEquals(4215746, service.findGlobalMaxThruster());
    }

}