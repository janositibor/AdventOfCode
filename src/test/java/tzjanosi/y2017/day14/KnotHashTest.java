package tzjanosi.y2017.day14;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class KnotHashTest {

    private static Stream<Arguments> hashTest() {
        return Stream.of(
                Arguments.of("flqrgnkx-0", "11010100"),
                Arguments.of("flqrgnkx-1", "01010101"),
                Arguments.of("flqrgnkx-2", "00001010"),
                Arguments.of("flqrgnkx-3", "10101101"),
                Arguments.of("flqrgnkx-4", "01101000"),
                Arguments.of("flqrgnkx-5", "11001001"),
                Arguments.of("flqrgnkx-6", "01000100"),
                Arguments.of("flqrgnkx-7", "11010110")
        );
    }

    @ParameterizedTest
    @MethodSource
    void hashTest(String input, String expected) {
        KnotHash knotHash = new KnotHash(input);
        assertThat(knotHash.createHash()).startsWith(expected);
    }
}