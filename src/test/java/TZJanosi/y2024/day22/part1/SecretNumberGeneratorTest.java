package TZJanosi.y2024.day22.part1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SecretNumberGeneratorTest {
    @Test
    void mixTest() {
        SecretNumberGenerator secretNumberGenerator = new SecretNumberGenerator(42, 1);
        assertEquals(37, secretNumberGenerator.mix(15));
    }

    @Test
    void pruneTest() {
        SecretNumberGenerator secretNumberGenerator = new SecretNumberGenerator(42, 1);
        assertEquals(16113920, secretNumberGenerator.prune(100000000));
    }

    @Test
    void generatorTest_1() {
        SecretNumberGenerator secretNumberGenerator = new SecretNumberGenerator(123, 1);
        assertEquals(15887950, secretNumberGenerator.createFinalValue());
    }

    @Test
    void generatorTest_10() {
        SecretNumberGenerator secretNumberGenerator = new SecretNumberGenerator(123, 10);
        assertEquals(5908254, secretNumberGenerator.createFinalValue());
    }

    private static Stream<Arguments> parameterizedGeneratorTest() {
        return Stream.of(
                Arguments.of(1, 15887950),
                Arguments.of(2, 16495136),
                Arguments.of(3, 527345),
                Arguments.of(4, 704524),
                Arguments.of(5, 1553684),
                Arguments.of(6, 12683156),
                Arguments.of(7, 11100544),
                Arguments.of(8, 12249484),
                Arguments.of(9, 7753432),
                Arguments.of(10, 5908254)

        );
    }

    @ParameterizedTest
    @MethodSource
    void parameterizedGeneratorTest(int numberOfIterations, long expectedResult) {
        SecretNumberGenerator secretNumberGenerator = new SecretNumberGenerator(123, numberOfIterations);
        assertEquals(expectedResult, secretNumberGenerator.createFinalValue());
    }

    @Test
    void generator2000Test_1() {
        SecretNumberGenerator secretNumberGenerator = new SecretNumberGenerator(2024, 2000);
        assertEquals(8667524, secretNumberGenerator.createFinalValue());
    }

    private static Stream<Arguments> paremeterizedGenerator2000Test() {
        return Stream.of(
                Arguments.of(1, 8685429),
                Arguments.of(10, 4700978),
                Arguments.of(100, 15273692),
                Arguments.of(2024, 8667524)
        );
    }

    @ParameterizedTest
    @MethodSource
    void paremeterizedGenerator2000Test(long initialValue, long expectedResult) {
        SecretNumberGenerator secretNumberGenerator = new SecretNumberGenerator(initialValue, 2000);
        assertEquals(expectedResult, secretNumberGenerator.createFinalValue());
    }
}