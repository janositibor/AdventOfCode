package TZJanosi.y2024.day16.part1;


import TZJanosi.y2024.day16.part1.Coordinate;
import TZJanosi.y2024.day16.part1.Labyrinth;
import TZJanosi.y2024.day16.part1.ReadData;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LabyrinthTest {
    private static Stream<Arguments> provideInputForMinimalCostTest() {
        return Stream.of(
                Arguments.of("testInput00.txt", 1010),
                Arguments.of("testInput01.txt", 2004),
                Arguments.of("testInput02.txt", 3007),
                Arguments.of("testInput03.txt", 1001),
                Arguments.of("testInput.txt", 7036),
                Arguments.of("testInput2.txt", 11048)
        );
    }

    @ParameterizedTest
    @MethodSource("provideInputForMinimalCostTest")
    void minimalCostTest(String fileName,int expectedResult){
        ReadData readData = new ReadData(fileName);
        Labyrinth labyrinth = new Labyrinth(readData.getOutput());
//        System.out.println("start: "+labyrinth.getStart());
//        System.out.println("end: "+labyrinth.getEnd());
        assertEquals(expectedResult,labyrinth.calculateWay());
    }

    @Test
    void problemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Labyrinth labyrinth = new Labyrinth(readData.getOutput());
        assertEquals(85480,labyrinth.calculateWay());
    }

}