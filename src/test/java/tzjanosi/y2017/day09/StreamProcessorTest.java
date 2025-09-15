package tzjanosi.y2017.day09;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class StreamProcessorTest {

    private static final Stream<Arguments> calculateTotalScoreTest() {
        return Stream.of(
                Arguments.of("{}", 1),
                Arguments.of("{{{}}}", 6),
                Arguments.of("{{},{}}", 5),
                Arguments.of("{{{},{},{{}}}}", 16),
                Arguments.of("{<a>,<a>,<a>,<a>}", 1),
                Arguments.of("{{<ab>},{<ab>},{<ab>},{<ab>}}", 9),
                Arguments.of("{{<!!>},{<!!>},{<!!>},{<!!>}}", 9),
                Arguments.of("{{<a!>},{<a!>},{<a!>},{<ab>}}", 3)
        );
    }


    @ParameterizedTest
    @MethodSource
    void calculateTotalScoreTest(String input, int result) {
        StreamProcessor streamProcessor = new StreamProcessor(input);
        assertEquals(result, streamProcessor.calculateTotalScore());
    }

    @Test
    void calculateTotalScoreProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        StreamProcessor streamProcessor = new StreamProcessor(readData.getOutput().get(0));
        assertEquals(10616, streamProcessor.calculateTotalScore());
    }
}