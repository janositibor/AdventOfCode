package tzjanosi.y2024.day20.part1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CheaterTest {

    private static Stream<Arguments> provideInputForTestDataTest() {
        return Stream.of(
                Arguments.of(65, 0),
                Arguments.of(64, 1),
                Arguments.of(41, 1),
                Arguments.of(40, 2),
                Arguments.of(39, 2),
                Arguments.of(38, 3),
                Arguments.of(3, 30),
                Arguments.of(2, 44)
        );
    }

    @ParameterizedTest
    @MethodSource("provideInputForTestDataTest")
    void testDataTest(int gainLimit, int result) {
        ReadData readData = new ReadData("testInput.txt");
        Cheater cheater = new Cheater(readData.getOutput(), gainLimit);
        assertEquals(84, cheater.getOriginalLength());
        assertEquals(result, cheater.calculateCheatRoutes());
    }

    @Test
    void problemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Cheater cheater = new Cheater(readData.getOutput(), 100);
        assertEquals(9432, cheater.getOriginalLength());

        assertEquals(1518, cheater.calculateCheatRoutes());
    }
}