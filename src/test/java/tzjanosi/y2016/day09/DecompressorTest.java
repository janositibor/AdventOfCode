package tzjanosi.y2016.day09;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DecompressorTest {

    public static Stream<Arguments> decompress() {
        return Stream.of(
                Arguments.of("ADVENT", 6, "ADVENT"),
                Arguments.of("A(1x5)BC", 7, "ABBBBBC"),
                Arguments.of("(3x3)XYZ", 9, "XYZXYZXYZ"),
                Arguments.of("A(2x2)BCD(2x2)EFG", 11, "ABCBCDEFEFG"),
                Arguments.of("(6x1)(1x3)A", 6, "(1x3)A"),
                Arguments.of("X(8x2)(3x3)ABCY", 18, "X(3x3)ABC(3x3)ABCY")
        );
    }

    @ParameterizedTest
    @MethodSource
    void decompress(String input, int length, String output) {
        Decompressor decompressor = new Decompressor(input);
        assertEquals(length, decompressor.decompress());
        assertEquals(output, decompressor.getDecompressed());
    }

    @Test
    void decompressProblemData() {
        ReadData readData = new ReadData("input.txt");
        Decompressor decompressor = new Decompressor(readData.getOutput().get(0));
        assertEquals(107035, decompressor.decompress());
    }

}