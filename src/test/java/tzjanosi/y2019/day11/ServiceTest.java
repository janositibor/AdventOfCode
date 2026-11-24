package tzjanosi.y2019.day11;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    public static Stream<Arguments> displayHullAsListTest() {
        return Stream.of(
                Arguments.of(0, "#  # ###   ##   ##  #### #     ##  ### "),
                Arguments.of(1, "#  # #  # #  # #  # #    #    #  # #  #"),
                Arguments.of(2, "#  # #  # #    #  # ###  #    #    #  #"),
                Arguments.of(3, "#  # ###  #    #### #    #    #    ### "),
                Arguments.of(4, "#  # # #  #  # #  # #    #    #  # #   "),
                Arguments.of(5, " ##  #  #  ##  #  # #    ####  ##  #   ")
        );
    }

    @Test
    void problemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Service service = new Service(readData.getOutput().get(0));
        service.run(0);
        assertEquals(2088, service.numberOfPaintedPoints());
    }

    @ParameterizedTest
    @MethodSource
    void displayHullAsListTest(int row, String expected) {
        ReadData readData = new ReadData("input.txt");
        Service service = new Service(readData.getOutput().get(0));
        service.run(1);
        assertEquals(expected, service.displayHullAsList().get(row));
    }

    @Test
    void problemDataPart2Test() {
        ReadData readData = new ReadData("input.txt");
        Service service = new Service(readData.getOutput().get(0));
        service.run(1);
        service.displayHull();
    }


}