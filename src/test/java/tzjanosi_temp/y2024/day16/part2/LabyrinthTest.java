package tzjanosi_temp.y2024.day16.part2;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LabyrinthTest {
    private static Stream<Arguments> lengthOfMinimalPathsTest() {
        return Stream.of(
                Arguments.of("testInput00.txt", 11),
                Arguments.of("testInput01.txt", 5),
                Arguments.of("testInput02.txt", 8),
                Arguments.of("testInput03.txt", 2),
                Arguments.of("testInput.txt", 45),
                Arguments.of("testInput2.txt", 64)
        );
    }

    @ParameterizedTest
    @MethodSource("lengthOfMinimalPathsTest")
    void lengthOfMinimalPathsTest(String fileName,int expectedResult){
        ReadData readData = new ReadData(fileName);
        Labyrinth labyrinth = new Labyrinth(readData.getOutput());
//        System.out.println("start: "+labyrinth.getStart());
//        System.out.println("end: "+labyrinth.getEnd());
        assertEquals(expectedResult,labyrinth.calculateWay());
    }

    @Test
    void lengthOfMinimalPathsTest00(){
        ReadData readData = new ReadData("testInput.txt");
        Labyrinth labyrinth = new Labyrinth(readData.getOutput());
        System.out.println("start: "+labyrinth.getStart());
        System.out.println("end: "+labyrinth.getEnd());
        assertEquals(45,labyrinth.calculateWay());
    }

    @Test
    void problemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Labyrinth labyrinth = new Labyrinth(readData.getOutput());
        assertEquals(518,labyrinth.calculateWay());
    }

}