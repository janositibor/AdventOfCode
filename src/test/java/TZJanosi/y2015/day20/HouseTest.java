package TZJanosi.y2015.day20;

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

    @ParameterizedTest
    @MethodSource("dividersTest")
    void dividersTest(int id, Set<Integer> expectedResult) {
        House house = new House(id);
        assertThat(house.getDividers()).containsAll(expectedResult);
    }

    @ParameterizedTest
    @MethodSource("presentsValueTest")
    void presentsValueTest(int id, int expectedResult) {
        House house = new House(id);
        assertEquals(expectedResult, house.getPresentsValue());
//        assertThat(haus.getDividers()).containsAll(expectedResult);
    }

}