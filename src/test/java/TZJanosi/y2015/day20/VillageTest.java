package TZJanosi.y2015.day20;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class VillageTest {

    private static Stream<Arguments> findTheLuckyPart1Test() {
        return Stream.of(
                Arguments.of(40, 3),
                Arguments.of(70, 4),
                Arguments.of(60, 4),
                Arguments.of(80, 6),
                Arguments.of(130, 8),
                Arguments.of(150, 8)
        );
    }

    @ParameterizedTest
    @MethodSource("findTheLuckyPart1Test")
    void findTheLuckyPart1Test(int targetToReach, int expectedResult) {
        Village village = new Village();
        assertEquals(expectedResult, village.findTheLuckyPart1(targetToReach));
    }

    @Test
    void findTheLuckyPart1ProblemDataTest() {
        Village village = new Village();
        assertEquals(776160, village.findTheLuckyPart1(33100000));
    }

    @Test
    void findTheLuckyPart2ProblemDataTest() {
        Village village = new Village();
        assertEquals(786240, village.findTheLuckyPart2(33100000));
    }

}