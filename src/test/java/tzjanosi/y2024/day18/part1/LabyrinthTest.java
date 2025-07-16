package tzjanosi.y2024.day18.part1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LabyrinthTest {
    private static Stream<Arguments> provideInputForMinimalCostTest() {
        return Stream.of(
                Arguments.of(6, "testInput00.txt", 22)
//                ,Arguments.of(70,"input1.txt", 322)
        );
    }

    @ParameterizedTest
    @MethodSource("provideInputForMinimalCostTest")
    void minimalCostTest(int size, String fileName, int expectedResult) {
        ReadData readData = new ReadData(fileName);
        Labyrinth labyrinth = new Labyrinth(size, readData.getOutput());
        assertEquals(expectedResult, labyrinth.calculateWay());
    }

    @Test
    void problemDataTest() {
        ReadData readData = new ReadData("input1.txt");
        Labyrinth labyrinth = new Labyrinth(70, readData.getOutput());
        assertEquals(322, labyrinth.calculateWay());
    }
}