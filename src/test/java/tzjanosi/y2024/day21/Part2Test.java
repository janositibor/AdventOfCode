package tzjanosi.y2024.day21;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Part2Test {

    private static Stream<Arguments> provideInputForDirectionalPadHandlerComplexityTest() {
        return Stream.of(
                Arguments.of("029A", 68 * 29),
                Arguments.of("980A", 60 * 980),
                Arguments.of("179A", 68 * 179),
                Arguments.of("456A", 64 * 456),
                Arguments.of("379A", 64 * 379)
        );
    }

    @ParameterizedTest
    @MethodSource("provideInputForDirectionalPadHandlerComplexityTest")
    void testComplexityWith3DirectionalPads(String input, int result) {
        DirectionalPadHandler directionalPadHandler = new DirectionalPadHandler();
        assertEquals(result, directionalPadHandler.complexityPart2(input, 3));
    }

    @Test
    void testComplexityWith3DirectionalPads() {
        DirectionalPadHandler directionalPadHandler = new DirectionalPadHandler();
        assertThat(directionalPadHandler.complexityPart2("029A", 3))
                .isEqualTo(68 * 29);
    }

    @Test
    void totalComplexityWithTestDataWith3DirectionalPads() {
        DirectionalPadHandler directionalPadHandler = new DirectionalPadHandler();
        assertEquals(126384, directionalPadHandler.totalComplexityPart2(List.of("029A", "980A", "179A", "456A", "379A"), 3));
    }

    @Test
    void totalComplexityWithProblemDataWith3DirectionalPads() {
        DirectionalPadHandler directionalPadHandler = new DirectionalPadHandler();
        assertEquals(219366, directionalPadHandler.totalComplexityPart2(List.of("340A", "586A", "839A", "413A", "968A"), 3));
    }

    @Test
    void totalComplexityWithTestDataWith4DirectionalPads_1() {
        DirectionalPadHandler directionalPadHandler = new DirectionalPadHandler();
        assertEquals(4756, directionalPadHandler.totalComplexityPart2(List.of("029A"), 4));
    }

    @Test
    void totalComplexityWithTestDataTestWith26DirectionalPads() {
        DirectionalPadHandler directionalPadHandler = new DirectionalPadHandler();
//        assertEquals(154115708116294L, directionalPadHandler.totalComplexityPart2(List.of("029A", "980A", "179A", "456A", "379A"),26));
        assertEquals(154115708116294L, directionalPadHandler.totalComplexityPart2(List.of("029A", "980A", "179A", "456A", "379A"), 26));
    }


    @Test
    void totalComplexityWithProblemDataTestWith26DirectionalPads() {
        DirectionalPadHandler directionalPadHandler = new DirectionalPadHandler();
        assertEquals(271631192020464L, directionalPadHandler.totalComplexityPart2(List.of("340A", "586A", "839A", "413A", "968A"), 26));
    }

}
