package tzjanosi.y2016.day17;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class HandlerTest {
    private static Stream<Arguments> findBestWayTest() {
        return Stream.of(
                Arguments.of("ihgpwlah", "DDRRRD"),
                Arguments.of("kglvqrro", "DDUDRLRRUDRD"),
                Arguments.of("ulqzkmiv", "DRURDRUDDLLDLUURRDULRLDUUDDDRR")
        );
    }

    private static Stream<Arguments> findLongestWayTest() {
        return Stream.of(
                Arguments.of("ihgpwlah", 370),
                Arguments.of("kglvqrro", 492),
                Arguments.of("ulqzkmiv", 830)
        );
    }

    @ParameterizedTest
    @MethodSource
    void findBestWayTest(String key, String result) {
        Handler handler = new Handler(key);
        assertEquals(result, handler.findBestWay());
    }

    @ParameterizedTest
    @MethodSource
    void findLongestWayTest(String key, int result) {
        Handler handler = new Handler(key);
        assertEquals(result, handler.findLongestWay());
    }

    @Test
    void findBestWayProblemDataTest() {
        Handler handler = new Handler("rrrbmfta");
        assertEquals("RLRDRDUDDR", handler.findBestWay());
    }

    @Test
    void findLongestWayProblemDataTest() {
        Handler handler = new Handler("rrrbmfta");
        assertEquals(420, handler.findLongestWay());
    }

}