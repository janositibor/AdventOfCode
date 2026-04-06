package tzjanosi.y2018.day14;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ChartTest {
    static Stream<Arguments> calculateScoreTest() {
        return Stream.of(Arguments.of(9, "5158916779"),
                Arguments.of(5, "0124515891"),
                Arguments.of(18, "9251071085"),
                Arguments.of(2018, "5941429882")
        );
    }

    @ParameterizedTest
    @MethodSource
    void calculateScoreTest(int input, String output) {
        Chart chart = new Chart();
        assertEquals(output, chart.calculateScore(input, 10));
    }

    @Test
    void calculateScoreProblemDataTest() {
        Chart chart = new Chart();
        assertEquals("6548103910", chart.calculateScore(768071, 10));
    }


}