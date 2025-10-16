package tzjanosi.y2017.day10.part2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class KnotHashTest {
    private static Stream<Arguments> hashTest() {
        return Stream.of(
                Arguments.of("", "a2582a3a0e66e6e86e3812dcb672a272"),
                Arguments.of("AoC 2017", "33efeb34ea91902bb2f59c9920caa6cd"),
                Arguments.of("1,2,3", "3efbe78a8d82f29979031a4aa0b16a9d"),
                Arguments.of("1,2,4", "63960835bcdc130f0b66d7ff4f6a5a8e")
        );
    }

    @ParameterizedTest
    @MethodSource
    void hashTest(String input, String expected) {
        KnotHash knotHash = new KnotHash(input);
        assertEquals(expected, knotHash.createHash());
    }

    @Test
    void hashWithProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        KnotHash knotHash = new KnotHash(readData.getOutput().get(0));
        assertEquals("70b856a24d586194331398c7fcfa0aaf", knotHash.createHash());
    }

}