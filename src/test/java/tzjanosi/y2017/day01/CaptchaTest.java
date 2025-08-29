package tzjanosi.y2017.day01;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CaptchaTest {
    private static Stream<Arguments> solveCaptchaTest() {
        return Stream.of(
                Arguments.of("1122", 3),
                Arguments.of("1111", 4),
                Arguments.of("1234", 0),
                Arguments.of("91212129", 9)
        );
    }

    @ParameterizedTest
    @MethodSource
    void solveCaptchaTest(String input, int expectedResult) {
        Captcha captcha = new Captcha(input);
        assertEquals(expectedResult, captcha.solveCaptcha());
    }

    @Test
    void solveCaptchaProblemDataTest() {
        ReadData readData = new ReadData("input.txt");
        Captcha captcha = new Captcha(readData.getOutput().get(0));
        assertEquals(1069, captcha.solveCaptcha());
    }

}