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

    @ParameterizedTest
    @MethodSource
    void findBestWayTest(String key, String result) {
        Handler handler = new Handler(key);
        assertEquals(result, handler.findBestWay());
    }

    @Test
    void findBestWayProblemDataTest() {
        Handler handler = new Handler("rrrbmfta");
        assertEquals("RLRDRDUDDR", handler.findBestWay());
    }

}