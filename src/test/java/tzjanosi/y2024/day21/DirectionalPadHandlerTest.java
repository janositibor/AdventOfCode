package tzjanosi.y2024.day21;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DirectionalPadHandlerTest {
    @Test
    void testCode() {
        DirectionalPadHandler directionalPadHandler = new DirectionalPadHandler();
        assertThat(directionalPadHandler.code("<A^A>^^AvvvA"))
                .hasSize(32)
                .contains("v<<A>>^A<A>AvA<^AA>A<vAAA>^A");
    }

    @Test
    void testCodeSet() {
        DirectionalPadHandler directionalPadHandler = new DirectionalPadHandler();
        assertThat(directionalPadHandler.codeSet(Set.of("<A^A>^^AvvvA")))
                .hasSize(32)
                .contains("v<<A>>^A<A>AvA<^AA>A<vAAA>^A");
    }

    @Test
    void testCodeTwice() {
        DirectionalPadHandler directionalPadHandler = new DirectionalPadHandler();
        assertThat(directionalPadHandler.codeTwice(Set.of("<A^A>^^AvvvA")))
                .hasSize(98304)
                .contains("<vA<AA>>^AvAA<^A>A<v<A>>^AvA^A<vA>^A<v<A>^A>AAvA^A<v<A>A>^AAAvA<^A>A");
    }

    @Test
    void testCodeTwiceRunningTime() {
        DirectionalPadHandler directionalPadHandler = new DirectionalPadHandler();
        assertThat(directionalPadHandler.codeTwice(Set.of("<A^A>^^AvvvA", "<A^A^>^AvvvA", "<A^A^^>AvvvA")))
                .hasSize(589824)
                .contains("<vA<AA>>^AvAA<^A>A<v<A>>^AvA^A<vA>^A<v<A>^A>AAvA^A<v<A>A>^AAAvA<^A>A");
    }

    @Test
    void testComplexityRunningTime() {
        DirectionalPadHandler directionalPadHandler = new DirectionalPadHandler();
        assertThat(directionalPadHandler.complexity("029A"))
                .isEqualTo(68 * 29);
    }

    private static Stream<Arguments> provideInputForDirectionalPadHandlerCodeTotalTest() {
        return Stream.of(
                Arguments.of("029A", "<vA<AA>>^AvAA<^A>A<v<A>>^AvA^A<vA>^A<v<A>^A>AAvA^A<v<A>A>^AAAvA<^A>A"),
                Arguments.of("980A", "<v<A>>^AAAvA^A<vA<AA>>^AvAA<^A>A<v<A>A>^AAAvA<^A>A<vA>^A<A>A"),
                Arguments.of("179A", "<v<A>>^A<vA<A>>^AAvAA<^A>A<v<A>>^AAvA^A<vA>^AA<A>A<v<A>A>^AAAvA<^A>A"),
                Arguments.of("456A", "<v<A>>^AA<vA<A>>^AAvAA<^A>A<vA>^A<A>A<vA>^A<A>A<v<A>A>^AAvA<^A>A"),
                Arguments.of("379A", "<v<A>>^AvA^A<vA<AA>>^AAvA<^A>AAvA^A<vA>^AA<A>A<v<A>A>^AAAvA<^A>A")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInputForDirectionalPadHandlerCodeTotalTest")
    void testCodeTotal(String input, String result) {
        DirectionalPadHandler directionalPadHandler = new DirectionalPadHandler(input);
        assertThat(directionalPadHandler.codeTotal())
                .contains(result);
    }

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
    void testComplexity(String input, int result) {
        DirectionalPadHandler directionalPadHandler = new DirectionalPadHandler();
        assertEquals(result, directionalPadHandler.complexity(input));
    }

    @Test
    void totalComplexityWithTestDataTest() {
        DirectionalPadHandler directionalPadHandler = new DirectionalPadHandler();
        assertEquals(126384, directionalPadHandler.totalComplexity(List.of("029A", "980A", "179A", "456A", "379A")));
    }

    @Test
    void totalComplexityWithProblemDataTest() {
        DirectionalPadHandler directionalPadHandler = new DirectionalPadHandler();
        assertEquals(219366, directionalPadHandler.totalComplexity(List.of("340A", "586A", "839A", "413A", "968A")));
    }

    @Test
    void testDeCode() {
        DirectionalPadHandler directionalPadHandler = new DirectionalPadHandler();
        assertEquals("<A^A>^^AvvvA", directionalPadHandler.deCode("v<<A>>^A<A>AvA<^AA>A<vAAA>^A"));

    }

    @Test
    void testDeCodeTwice() {
        DirectionalPadHandler directionalPadHandler = new DirectionalPadHandler();
        assertEquals("<A^A>^^AvvvA", directionalPadHandler.deCodeTwice("<vA<AA>>^AvAA<^A>A<v<A>^>AvA^A<vA>^A<Av<A>^>AAvA^A<v<A>A>^AAAvA^<A>A"));
    }

    @ParameterizedTest
    @MethodSource("provideInputForDirectionalPadHandlerCodeTotalTest")
    void testDeCodeTotal(String result, String input) {
        DirectionalPadHandler directionalPadHandler = new DirectionalPadHandler();
        assertEquals(result, directionalPadHandler.deCodeTotal(input));
    }

    private static Stream<Arguments> provideInputForProblemDataDeCodeTotalTest() {
        return Stream.of(
                Arguments.of("340A", "v<<A>>^AvA^Av<A<AA>>^AAvA^<A>AvA^Av<A^>Av<<A>>^AA<Av>A^Av<A^>A<A>A"),
                Arguments.of("586A", "v<<A>>^AAv<A<A>>^AvAA^<A>Av<<A>>^AvA^Av<A^>Av<<A>>^A<Av>A^Av<A<A>>^AA<Av>A^A"),
                Arguments.of("839A", "v<<A>>^AAAv<A<A>>^AvAA^<A>Av<A^>Av<<A>>^AA<Av>A^Av<<A>>^AAvA^Av<A<A>>^AAA<Av>A^A"),
                Arguments.of("413A", "v<<A>>^AAv<A<A>>^AAvAA^<A>Av<A<A>>^A<Av>A^Av<A^>AA<A>Av<A<A>>^A<Av>A^A"),
                Arguments.of("968A", "v<<A>>^AAAvA^Av<A<A>>^A<Av>A^Av<A<AA>>^AvA^<A>AvA^Av<A^>Av<<A>>^AAA<Av>A^A")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInputForProblemDataDeCodeTotalTest")
    void testProblemDataDeCodeTotal(String result, String input) {
        DirectionalPadHandler directionalPadHandler = new DirectionalPadHandler();
        assertEquals(result, directionalPadHandler.deCodeTotal(input));
    }
}