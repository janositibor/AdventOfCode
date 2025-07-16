package tzjanosi_temp.y2015.day20;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HouseTest {
    private static Stream<Arguments> dividersTest() {
        return Stream.of(
                Arguments.of(1, Set.of(1)),
                Arguments.of(2, Set.of(1, 2)),
                Arguments.of(3, Set.of(1, 3)),
                Arguments.of(4, Set.of(1, 2, 4)),
                Arguments.of(5, Set.of(1, 5)),
                Arguments.of(6, Set.of(1, 2, 3, 6))
        );
    }

    private static Stream<Arguments> presentsValueTest() {
        return Stream.of(
                Arguments.of(1, 10),
                Arguments.of(2, 30),
                Arguments.of(3, 40),
                Arguments.of(4, 70),
                Arguments.of(5, 60),
                Arguments.of(6, 120),
                Arguments.of(7, 80),
                Arguments.of(8, 150),
                Arguments.of(9, 130)
        );
    }

    private static Stream<Arguments> presentsValuePart2Test() {
        return Stream.of(
                Arguments.of(1, 11),
                Arguments.of(2, 33),
                Arguments.of(3, 44),
                Arguments.of(4, 77),
                Arguments.of(5, 66),
                Arguments.of(6, 132),
                Arguments.of(7, 88),
                Arguments.of(8, 165),
                Arguments.of(9, 143)
        );
    }

    @ParameterizedTest
    @MethodSource("dividersTest")
    void dividersTest(int id, Set<Integer> expectedResult) {
        House house = new House(id);
        house.getPresentsValuePart1();
        assertThat(house.getDividers()).containsAll(expectedResult);
    }

    @ParameterizedTest
    @MethodSource("presentsValueTest")
    void presentsValueTest(int id, int expectedResult) {
        House house = new House(id);
        assertEquals(expectedResult, house.getPresentsValuePart1());
    }

    @ParameterizedTest
    @MethodSource("presentsValuePart2Test")
    void presentsValuePart2Test(int id, int expectedResult) {
        House house = new House(id);
        assertEquals(expectedResult, house.getPresentsValuePart2());
    }

    @Test
    void part2Test() {
        House house;
        house = new House(50);
        assertEquals(house.getPresentsValuePart1() / 10, (house.getPresentsValuePart2()) / 11);
        house = new House(51);
        assertEquals(house.getPresentsValuePart1() / 10, (house.getPresentsValuePart2() / 11) + 1);
        house = new House(100);
        assertEquals(house.getPresentsValuePart1() / 10, (house.getPresentsValuePart2() / 11) + 1);
        house = new House(101);
        assertEquals(house.getPresentsValuePart1() / 10, (house.getPresentsValuePart2() / 11) + 1);
        house = new House(102);
        assertEquals(house.getPresentsValuePart1() / 10, (house.getPresentsValuePart2() / 11) + 1 + 2);
        house = new House(150);
        assertEquals(house.getPresentsValuePart1() / 10, (house.getPresentsValuePart2() / 11) + 1 + 2);
        house = new House(151);
        assertEquals(house.getPresentsValuePart1() / 10, (house.getPresentsValuePart2() / 11) + 1);
        house = new House(152);
        assertEquals(house.getPresentsValuePart1() / 10, (house.getPresentsValuePart2() / 11) + 1 + 2);
        house = new House(153);
        assertEquals(house.getPresentsValuePart1() / 10, (house.getPresentsValuePart2() / 11) + 1 + 3);
    }

}