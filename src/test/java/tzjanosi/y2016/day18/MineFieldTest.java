package tzjanosi.y2016.day18;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MineFieldTest {
    private static Stream<Arguments> calculateNextRowTest() {
        return Stream.of(
                Arguments.of("..^^.", ".^^^^"),
                Arguments.of(".^^^^", "^^..^"),
                Arguments.of(".^^.^.^^^^", "^^^...^..^"),
                Arguments.of("^^^...^..^", "^.^^.^.^^."),
                Arguments.of("^.^^.^.^^.", "..^^...^^^")
        );
    }

    @ParameterizedTest
    @MethodSource
    void calculateNextRowTest(String input, String result) {
        MineField mineField = new MineField(input);
        assertEquals(result, mineField.checkCalculateNextRow());
    }

    @Test
    void calculateRowsTest() {
        MineField mineField = new MineField("..^^.");
        assertEquals(6, mineField.calculateRows(2));
    }

    @Test
    void calculateRows2Test() {
        MineField mineField = new MineField(".^^.^.^^^^");
        assertEquals(38, mineField.calculateRows(9));
    }

    @Test
    void calculateRowsProgramDataTest() {
        ReadData readData = new ReadData("input.txt");
        MineField mineField = new MineField(readData.getOutput().get(0));
        assertEquals(1987, mineField.calculateRows(40 - 1));
    }
}