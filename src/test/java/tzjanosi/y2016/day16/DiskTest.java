package tzjanosi.y2016.day16;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DiskTest {
    static Stream<Arguments> checkExtension() {
        return Stream.of(
                Arguments.of("1", "100"),
                Arguments.of("0", "001"),
                Arguments.of("11111", "11111000000"),
                Arguments.of("111100001010", "1111000010100101011110000")
        );
    }

    @ParameterizedTest
    @MethodSource
    void checkExtension(String input, String result) {
        Disk disk = new Disk(0, input);
        assertEquals(result, disk.extendString(input));
    }

    @Test
    void eraseTest() {
        Disk disk = new Disk(20, "10000");
        assertEquals("01100", disk.erase());
    }

    @Test
    void eraseProblemDataTest() {
        Disk disk = new Disk(272, "11011110011011101");
        assertEquals("00000100100001100", disk.erase());
    }

    @Test
    void eraseProblemDataPart2Test() {
        Disk disk = new Disk(35651584, "11011110011011101");
        assertEquals("00011010100010010", disk.erase());
    }


}