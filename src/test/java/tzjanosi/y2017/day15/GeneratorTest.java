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

    private static Stream<Arguments> pickyGeneratorATest() {
        return Stream.of(
                Arguments.of(65, 1352636452),
                Arguments.of(1352636452, 1992081072),
                Arguments.of(1992081072, 530830436),
                Arguments.of(530830436, 1980017072),
                Arguments.of(1980017072, 740335192)
        );
    }

    private static Stream<Arguments> pickyGeneratorBTest() {
        return Stream.of(
                Arguments.of(8921, 1233683848),
                Arguments.of(1233683848, 862516352),
                Arguments.of(862516352, 1159784568),
                Arguments.of(1159784568, 1616057672),
                Arguments.of(1616057672, 412269392)
        );
    }

    @ParameterizedTest
    @MethodSource
    void generatorATest(long input, long expected) {
        Generator generatorA = new Generator(16807, 4);
        assertEquals(expected, generatorA.generate(input));
    }

    @ParameterizedTest
    @MethodSource
    void generatorBTest(long input, long expected) {
        Generator generatorA = new Generator(48271, 8);
        assertEquals(expected, generatorA.generate(input));
    }

    @ParameterizedTest
    @MethodSource
    void pickyGeneratorATest(long input, long expected) {
        Generator generatorB = new Generator(16807, 4);
        assertEquals(expected, generatorB.generatePicky(input));
    }

    @ParameterizedTest
    @MethodSource
    void pickyGeneratorBTest(long input, long expected) {
        Generator generatorB = new Generator(48271, 8);
        assertEquals(expected, generatorB.generatePicky(input));
    }

}