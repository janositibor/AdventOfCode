package TZJanosi.y2024.day20.part2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CheaterTest {
    private static Stream<Arguments> provideInputForTestDataTest() {
        return Stream.of(
                Arguments.of(77, 0),
                Arguments.of(76, 3),
                Arguments.of(75, 3),
                Arguments.of(74, 7),
                Arguments.of(73, 7),
                Arguments.of(72, 29),
                Arguments.of(52, 253),
                Arguments.of(50, 285)
//                ,Arguments.of(70,"input1.txt", 322)
        );
    }

    @ParameterizedTest
    @MethodSource("provideInputForTestDataTest")
    void testDataTest(int gainLimit, int result) {
        ReadData readData = new ReadData("testInput.txt");
        Cheater cheater = new Cheater(readData.getOutput(), gainLimit);
        assertEquals(84, cheater.getOriginalLength());
//        System.out.println(cheater.getWallsToOmit());

        assertEquals(result, cheater.calculateCheatWithDistance(20));
//        System.out.println(cheater.getResults());
    }

    @Test
    void problemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Cheater cheater = new Cheater(readData.getOutput(), 100);
        assertEquals(9432, cheater.getOriginalLength());
//        System.out.println(cheater.getWallsToOmit());

        assertEquals(1032257, cheater.calculateCheatWithDistance(20));
//        System.out.println(cheater.getResults());
    }

}