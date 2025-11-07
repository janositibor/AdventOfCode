package tzjanosi.y2017.day15;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GeneratorTest {
    private static Stream<Arguments> generatorATest() {
        return Stream.of(
                Arguments.of(65, 1092455),
                Arguments.of(1092455, 1181022009),
                Arguments.of(1181022009, 245556042),
                Arguments.of(245556042, 1744312007),
                Arguments.of(1744312007, 1352636452)
        );
    }

    private static Stream<Arguments> generatorBTest() {
        return Stream.of(
                Arguments.of(8921, 430625591),
                Arguments.of(430625591, 1233683848),
                Arguments.of(1233683848, 1431495498),
                Arguments.of(1431495498, 137874439),
                Arguments.of(137874439, 285222916)
        );
    }

    @ParameterizedTest
    @MethodSource
    void generatorATest(long input, long expected) {
        Generator generatorA = new Generator(16807);
        assertEquals(expected, generatorA.generate(input));
    }

    @ParameterizedTest
    @MethodSource
    void generatorBTest(long input, long expected) {
        Generator generatorA = new Generator(48271);
        assertEquals(expected, generatorA.generate(input));
    }

}